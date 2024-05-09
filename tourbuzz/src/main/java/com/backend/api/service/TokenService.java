package com.backend.api.service;

import com.backend.api.repository.sqlite.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    @Transactional(value = "sqliteTxManager")
    public String storeTokenAndKey(String email, String token){
        String key = null;
        while (key == null){
            String randomKey = tokenRepository.getRandomKey();
            int count = tokenRepository.countKey(randomKey);
            if(count == 0) key = randomKey;
        }
        tokenRepository.createKey(key, email, token);
        return key;
    }

    public String getValueByKeyAndEmail(String key, String email){
        return tokenRepository.getValueByKeyAndEmail(key, email);
    }

    public void removeKey(String key){
        tokenRepository.removeKey(key);
    }
}
