package com.backend.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.backend.api.Constants;
import com.backend.api.mail.EmailSenderService;
import com.backend.api.repository.postgres.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {
    @Value("${local}")
    private boolean local;

    @Value("${jwt.magic-token.expiration-time}")
    private long magicTokenExpTime;

    @Value("${jwt.magic-token.secret-key}")
    private String magicTokenSecret;

    @Value("${jwt.access-token.expiration-time}")
    private long accessTokenExpTime;

    @Value("${jwt.access-token.secret-key}")
    private String accessTokenSecret;

    @Autowired
    HttpServletRequest httpServletRequest;

    private final UserRepository userRepository;

    private final EmailSenderService emailSenderService;

    private final AdminService adminService;

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

    public void emailMagicLink(String email) throws MessagingException {
        String token = generateMagicToken(email);
        emailSenderService.sendMagicLink(email, token);
    }

    @Transactional
    public String getEmailFromMagicToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(magicTokenSecret)).build();
        DecodedJWT decodedJWT;
        try {
            decodedJWT = verifier.verify(token);
        } catch (Exception e){
            throw new Exception(Constants.LOGIN_INVALID_LINK);
        }
        if(decodedJWT.getExpiresAt().before(new Date())){
            throw new CredentialsExpiredException(Constants.LOGIN_EXPIRED_LINK);
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
            throw new Exception(Constants.LOGIN_INVALID_TOKEN);
        }
        if(decodedJWT.getExpiresAt().before(new Date())){
            throw new CredentialsExpiredException(Constants.LOGIN_EXPIRED_TOKEN);
        }
        return decodedJWT.getSubject();
    }

    public String getLoggedInEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    
    public void verifyAdminHeaderToken() throws Exception {
        String token = httpServletRequest.getHeader(Constants.ADMIN_HEADER_NAME);
        verifyAdminToken(token);
    }

    public void verifyAdminToken(String token) throws Exception {
        String secret = adminService.getSecret();
        System.out.println(token);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            long issuedTimeMillis = decodedJWT.getIssuedAt().getTime();
            long currentTimeMillis = System.currentTimeMillis();
            long oneHourInMillis = 60 * 60 * 1000;
            if (currentTimeMillis - issuedTimeMillis > oneHourInMillis) {
                throw new Exception();
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new Exception(Constants.LOGIN_INVALID_TOKEN);
        }
    }

    public String generateAdminToken() {
        String secret = adminService.getSecret();
        return JWT.create()
                .withIssuer(RandomStringUtils.randomAlphanumeric(16))
                .withIssuedAt(Instant.now())
                .sign(Algorithm.HMAC256(secret));
    }
}
