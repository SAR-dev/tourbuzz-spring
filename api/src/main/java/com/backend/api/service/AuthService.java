package com.backend.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Value("${jwt.magictoken.expirationtime}")
    private long magicTokenExpTime;

    @Value("${jwt.magictoken.secretkey}")
    private String magicTokenSecret;

    public String getMagicToken(String email){
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(Instant.now())
                .withExpiresAt(now.plus(magicTokenExpTime, ChronoUnit.SECONDS))
                .sign(Algorithm.HMAC256(magicTokenSecret));
    }

    public void emailMagicLink(String email){
        String token = getMagicToken(email);
        System.out.println(token);
//        create the link
    }
}
