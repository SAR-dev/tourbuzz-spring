package com.backend.api.service;

import com.backend.api.Constants;
import com.backend.api.GlobalStore;
import com.backend.api.enums.PaymentType;
import com.backend.api.request.BkashPaymentCreateReq;
import com.backend.api.response.PaymentRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BkashService {
    private final GlobalStore globalStore;
    private final PaymentService paymentService;

    public String getBkashInvoiceNo(){
        return paymentService.getInvoiceNo(PaymentType.BKASH);
    }

    public String getPaymentLink(BkashPaymentCreateReq paymentCreateReq) throws Exception {
        String idToken = globalStore.getIdToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization", idToken);
        headers.set("x-app-key", Constants.BKASH.API_KEY);

        Map<String, String> payload = new HashMap<>();
        payload.put("mode", Constants.BKASH.DEFAULT_MODE);
        payload.put("currency", Constants.BKASH.DEFAULT_CURRENCY);
        payload.put("intent", Constants.BKASH.DEFAULT_INTENT);
        payload.put("callbackURL", Constants.BKASH.DEFAULT_CALLBACK_URL);
        payload.put("payerReference", paymentCreateReq.getPayerReference());
        payload.put("merchantInvoiceNumber", paymentCreateReq.getInvoiceNo());
        payload.put("amount", String.valueOf(paymentCreateReq.getAmount()));

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<Map> responseEntity = new RestTemplate().postForEntity(Constants.BKASH.CREATE_PAYMNET_API, requestEntity, Map.class);

        Map response = responseEntity.getBody();
        if (response == null || !response.containsKey("bkashURL") || !response.containsKey("paymentID")) {
            throw new RuntimeException("Bkash URL not found");
        }

        String paymentId = (String) response.get("paymentID");
        String bkashURL = (String) response.get("bkashURL");
        String invoiceNo = paymentCreateReq.getInvoiceNo();
        double paymentAmount = paymentCreateReq.getAmount();
        paymentService.registerPaymentIdWithInvoice(invoiceNo, paymentId, paymentAmount);

        return bkashURL;
    }

    public ResponseEntity<?> handlePaymentCallback(String paymentID) throws Exception {
        String idToken = globalStore.getIdToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("authorization", idToken);
        headers.set("x-app-key", Constants.BKASH.API_KEY);

        Map<String, String> payload = new HashMap<>();
        payload.put("paymentID", paymentID);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<Map> responseEntity = new RestTemplate().postForEntity(Constants.BKASH.EXECUTE_PAYMENT_API, requestEntity, Map.class);

        Map response = responseEntity.getBody();
        assert response != null;
        String statusCode = (String) response.get("statusCode");

        if (!statusCode.equals("0000")) {
            throw new RuntimeException("Payment not successful");
        }

        PaymentRes paymentRes = PaymentRes.builder()
                .paymentId((String) response.get("paymentID"))
                .transactionId((String) response.get("trxID"))
                .paymentAmount((Double) response.get("amount"))
                .currency((String) response.get("currency"))
                .paidAt((LocalDateTime) response.get("paymentExecuteTime"))
                .referenceNo((String) response.get("payerReference"))
                .build();
        paymentService.completePaymentWithPaymentId(paymentRes);
        return ResponseEntity.of(Optional.of(paymentRes));

    }


}
