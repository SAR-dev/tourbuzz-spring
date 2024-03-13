package com.backend.api.service;

import com.backend.api.GlobalStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BkashService {
    private final RestTemplate restTemplate;
    private final GlobalStore globalStore;

    public String getIdToken(){
        return globalStore.getIdToken();
    }
}
