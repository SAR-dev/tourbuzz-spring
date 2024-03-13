package com.backend.api.service;

import com.backend.api.Constants;
import com.backend.api.GlobalStore;
import com.backend.api.request.BkashPaymentCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BkashService {
    private final RestTemplate restTemplate;
    private final GlobalStore globalStore;

    public String getPaymentLink(BkashPaymentCreateReq paymentCreateReq){
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
        payload.put("merchantInvoiceNumber", paymentCreateReq.getMerchantInvoiceNumber());
        payload.put("amount", String.valueOf(paymentCreateReq.getAmount()));

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<Map> responseEntity = new RestTemplate().postForEntity(Constants.BKASH.CREATE_PAYMNET_API, requestEntity, Map.class);

        Map response = responseEntity.getBody();
        if (response == null || !response.containsKey("bkashURL")) {
            throw new RuntimeException("Bkash URL not found");
        }

        return (String) response.get("bkashURL");
    }

    public ResponseEntity<?> handlePaymentCallback(String paymentID){
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
        if (response == null || !response.containsKey("statusCode")) {
            throw new RuntimeException("Payment not successful");
        }
        if(response.get("statusCode") != "0000"){
            throw new RuntimeException("Payment failed");
        }

//        {
//            "paymentID": "TR0011IrU8fOT1710333508252",
//                "trxID": "BCD30HAD8F",
//                "transactionStatus": "Completed",
//                "amount": "1",
//                "currency": "BDT",
//                "intent": "sale",
//                "paymentExecuteTime": "2024-03-13T18:38:55:682 GMT+0600",
//                "merchantInvoiceNumber": "01619777282",
//                "payerReference": "Inv972842984876022",
//                "customerMsisdn": "01619777282",
//                "statusCode": "0000",
//                "statusMessage": "Successful"
//        }

        return ResponseEntity.of(Optional.of(response));

    }
}
