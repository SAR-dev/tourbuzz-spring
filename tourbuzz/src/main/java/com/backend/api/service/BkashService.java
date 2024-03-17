package com.backend.api.service;

import com.backend.api.Constants;
import com.backend.api.GlobalStore;
import com.backend.api.enums.PaymentType;
import com.backend.api.request.InvoiceCreateReq;
import com.backend.api.response.InvoicePaymentRes;
import com.backend.api.response.PaymentRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BkashService {
    private final GlobalStore globalStore;
    private final PaymentService paymentService;

    @Transactional
    public InvoicePaymentRes getInvoicePaymentRes(InvoiceCreateReq invoiceCreateReq) throws Exception {
        String invoiceNo = getBkashInvoiceNo(invoiceCreateReq);
        String paymentLink = getPaymentLink(invoiceNo);
        return InvoicePaymentRes.builder().invoice_no(invoiceNo).payment_link(paymentLink).build();
    }

    public String getBkashInvoiceNo(InvoiceCreateReq invoiceCreateReq) throws Exception {
        return paymentService.getInvoiceNo(PaymentType.BKASH, invoiceCreateReq);
    }

    private HttpHeaders getDefaultHeaders(){
        String idToken = globalStore.getIdToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization", idToken);
        headers.set("x-app-key", Constants.BKASH.API_KEY);
        return headers;
    }

    private Timestamp getBkashPaidAt(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss:SSS 'GMT'Z");
        OffsetDateTime dateTime = OffsetDateTime.parse(dateString, formatter);
        return Timestamp.valueOf(dateTime.toLocalDateTime());
    }

    private static Map<String, String> getPaymentPayload(
            String invoiceNo,
            double paymentAmount
    ) {
        Map<String, String> payload = new HashMap<>();
        payload.put("mode", Constants.BKASH.DEFAULT_MODE);
        payload.put("currency", Constants.BKASH.DEFAULT_CURRENCY);
        payload.put("intent", Constants.BKASH.DEFAULT_INTENT);
        payload.put("callbackURL", Constants.BKASH.DEFAULT_CALLBACK_URL);
        payload.put("payerReference", "000");
        payload.put("merchantInvoiceNumber", invoiceNo);
        payload.put("amount", String.valueOf(paymentAmount));

        return payload;
    }

    private PaymentRes handlePayment(ResponseEntity<Map> responseEntity) throws Exception {
        Map response = responseEntity.getBody();
        assert response != null;
        String statusCode = (String) response.get("statusCode");

        if (!statusCode.equals("0000")) {
            throw new RuntimeException("Payment not successful");
        }

        PaymentRes paymentRes = PaymentRes.builder()
                .paymentId((String) response.get("paymentID"))
                .transactionId((String) response.get("trxID"))
                .paymentAmount(Double.parseDouble((String) response.get("amount")))
                .currency((String) response.get("currency"))
                .paidAt(getBkashPaidAt((String) response.get("paymentExecuteTime")))
                .referenceNo((String) response.get("payerReference"))
                .completed(response.get("transactionStatus").equals("Completed"))
                .build();
        paymentService.updatePaymentWithPaymentId(paymentRes);
        return paymentRes;
    }

    public String getPaymentLink(String invoiceNo) throws Exception {
        double paymentAmount = paymentService.getPaymentAmountByInvoiceNo(invoiceNo);
        HttpHeaders headers = getDefaultHeaders();

        Map<String, String> payload = getPaymentPayload(invoiceNo, paymentAmount);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<Map> responseEntity = new RestTemplate().postForEntity(
                Constants.BKASH.CREATE_PAYMNET_API,
                requestEntity,
                Map.class
        );

        Map response = responseEntity.getBody();
        if (response == null || !response.containsKey("bkashURL") || !response.containsKey("paymentID")) {
            throw new RuntimeException("Bkash URL not found");
        }

        String paymentId = (String) response.get("paymentID");
        String bkashURL = (String) response.get("bkashURL");

        paymentService.registerPaymentIdWithInvoice(invoiceNo, paymentId, paymentAmount);

        return bkashURL;
    }

    public PaymentRes handlePaymentCallback(String paymentID) throws Exception {
        HttpHeaders headers = getDefaultHeaders();

        Map<String, String> payload = new HashMap<>();
        payload.put("paymentID", paymentID);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<Map> responseEntity = new RestTemplate().postForEntity(
                Constants.BKASH.EXECUTE_PAYMENT_API,
                requestEntity,
                Map.class
        );

        return handlePayment(responseEntity);
    }

    public PaymentRes queryPaymentByInvoiceNo(String invoiceNo) throws Exception {
        String paymentID = paymentService.getPaymentIdByInvoiceNo(invoiceNo);

        HttpHeaders headers = getDefaultHeaders();

        Map<String, String> payload = new HashMap<>();
        payload.put("paymentID", paymentID);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<Map> responseEntity = new RestTemplate().postForEntity(
                Constants.BKASH.QUERY_PAYMENT_API,
                requestEntity,
                Map.class
        );
        return handlePayment(responseEntity);
    }
}
