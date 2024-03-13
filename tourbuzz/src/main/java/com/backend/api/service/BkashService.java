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

        System.out.println(response);
        if (response == null || !response.containsKey("paymentID")) {
            throw new RuntimeException("Bkash URL not found");
        }

        return (ResponseEntity<?>) response.get("paymentID");

    }
}
