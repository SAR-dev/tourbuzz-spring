package com.backend.api;

import com.backend.api.service.BkashService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class GlobalStore {
    private String BKASH_ID_TOKEN = "";

    @Bean @Lazy
    public String getBKASH_ID_TOKEN(){
        return BKASH_ID_TOKEN;
    }

    @Bean @Lazy
    @Scheduled(fixedRate = 10000)
    public void refreshToken() {
        BKASH_ID_TOKEN = getIdToken();
    }

    public String getIdToken(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("username", Constants.BKASH.USERNAME);
        headers.set("password", Constants.BKASH.PASSWORD);

        Map<String, String> payload = new HashMap<>();
        payload.put("app_key", Constants.BKASH.API_KEY);
        payload.put("app_secret", Constants.BKASH.API_SECRET);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<Map> responseEntity = new RestTemplate().postForEntity(Constants.BKASH.GRANT_TOKEN_API, requestEntity, Map.class);

        Map response = responseEntity.getBody();
        if (response == null || !response.containsKey("id_token")) {
            throw new RuntimeException("Id token not found");
        }

        return (String) response.get("id_token");
    }
}
