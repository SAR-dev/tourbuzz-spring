package com.backend.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.backend.api.repository.postgres.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Value("${jwt.magictoken.expirationtime}")
    private long magicTokenExpTime;

    @Value("${jwt.magictoken.secretkey}")
    private String magicTokenSecret;

    @Value("${jwt.accesstoken.expirationtime}")
    private long accessTokenExpTime;

    @Value("${jwt.accesstoken.secretkey}")
    private String accessTokenSecret;

    private final UserRepository userRepository;

    public String generateMagicToken(String email){
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(Instant.now())
                .withExpiresAt(now.plus(magicTokenExpTime, ChronoUnit.SECONDS))
                .sign(Algorithm.HMAC256(magicTokenSecret));
    }

    public String generateAccessToken(String email){
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(Instant.now())
                .withExpiresAt(now.plus(accessTokenExpTime, ChronoUnit.SECONDS))
                .sign(Algorithm.HMAC256(accessTokenSecret));
    }

    public void emailMagicLink(String email){
        String token = generateMagicToken(email);
        System.out.println(token);
    }

    @Transactional
    public String getEmailFromMagicToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(magicTokenSecret)).build();
        DecodedJWT decodedJWT;
        try {
            decodedJWT = verifier.verify(token);
        } catch (Exception e){
            throw new Exception("Invalid link");
        }
        if(decodedJWT.getExpiresAt().before(new Date())){
            throw new CredentialsExpiredException("Link expired.");
        }
        return decodedJWT.getSubject();
    }

    @Transactional
    public String getAccessToken(String token) throws Exception {
        String email = getEmailFromMagicToken(token);
        userRepository.createUserByEmail(email);
        return generateAccessToken(email);
    }

    @Transactional
    public String getEmailFromAuthToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(accessTokenSecret)).build();
        DecodedJWT decodedJWT;
        try {
            decodedJWT = verifier.verify(token);
        } catch (Exception e){
            throw new Exception("Invalid token");
        }
        if(decodedJWT.getExpiresAt().before(new Date())){
            throw new CredentialsExpiredException("Token expired.");
        }
        return decodedJWT.getSubject();
    }
}
