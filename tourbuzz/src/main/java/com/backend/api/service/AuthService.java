package com.backend.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.backend.api.Constants;
import com.backend.api.ErrorMessages;
import com.backend.api.mail.EmailSenderService;
import com.backend.api.repository.postgres.UserRepository;
import com.backend.api.request.UserSignInReq;
import com.backend.api.request.admin.AdminSignInReq;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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
    @Autowired
    HttpServletRequest httpServletRequest;

    private final UserRepository userRepository;

    private final EmailSenderService emailSenderService;

    private final AdminService adminService;

//    Magic Link
    public String generateMagicToken(String email){
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(now)
                .withExpiresAt(now.plus(Constants.JWT.MAGIC_TOKEN_EXPIRE_AFTER, ChronoUnit.SECONDS))
                .sign(Algorithm.HMAC256(Constants.JWT.MAGIC_TOKEN_SECRET_KEY));
    }

    public void emailMagicLink(String email) throws MessagingException {
        String token = generateMagicToken(email);
        emailSenderService.sendMagicLink(email, token);
    }

    @Transactional
    public String getEmailFromMagicToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Constants.JWT.MAGIC_TOKEN_SECRET_KEY)).build();
        DecodedJWT decodedJWT;
        try {
            decodedJWT = verifier.verify(token);
        } catch (Exception e){
            throw new Exception(ErrorMessages.LOGIN_INVALID_LINK);
        }
        if(decodedJWT.getExpiresAt().before(new Date())){
            throw new CredentialsExpiredException(ErrorMessages.LOGIN_EXPIRED_LINK);
        }
        return decodedJWT.getSubject();
    }

//    Access Token
    public String generateAccessToken(String email, String username){
        Instant now = Instant.now();
        return JWT.create()
                .withIssuer(email)
                .withSubject(username)
                .withIssuedAt(now)
                .withExpiresAt(now.plus(Constants.JWT.ACCESS_TOKEN_EXPIRE_AFTER, ChronoUnit.SECONDS))
                .sign(Algorithm.HMAC256(Constants.JWT.ACCESS_TOKEN_SECRET_KEY));
    }

    @Transactional
    public String getAccessToken(String token) throws Exception {
        String email = getEmailFromMagicToken(token);
        String username = userRepository.createUserByEmail(email);
        return generateAccessToken(email, username);
    }

    @Transactional
    public String getEmailFromAccessToken(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Constants.JWT.ACCESS_TOKEN_SECRET_KEY)).build();
        DecodedJWT decodedJWT;
        try {
            decodedJWT = verifier.verify(token);
        } catch (Exception e){
            throw new Exception(ErrorMessages.INVALID_TOKEN);
        }
        if(decodedJWT.getExpiresAt().before(new Date())){
            throw new CredentialsExpiredException(ErrorMessages.EXPIRED_TOKEN);
        }
        return decodedJWT.getIssuer();
    }

    public String getLoggedInEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof AnonymousAuthenticationToken) return null;
        return auth.getName();
    }

//    Admin Key
    public String generateAdminKey(String email) {
        String secret = adminService.getSecret();
        Instant now = Instant.now();
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(now)
                .withExpiresAt(now.plus(Constants.JWT.ADMIN_KEY_EXPIRE_AFTER_IN_SEC, ChronoUnit.SECONDS))
                .sign(Algorithm.HMAC256(secret));
    }

    @Transactional
    public String getEmailFromAdminKey(String adminKey) {
        String secret = adminService.getSecret();
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT decodedJWT = verifier.verify(adminKey);
        if(decodedJWT.getExpiresAt().before(new Date())){
            throw new CredentialsExpiredException(ErrorMessages.EXPIRED_TOKEN);
        }
        return decodedJWT.getSubject();
    }

    public void verifySignInAdminRequest(AdminSignInReq signInReq) throws Exception {
        String magicEmail = getEmailFromMagicToken(signInReq.getMagicToken());
        String adminEmail = getEmailFromAdminKey(signInReq.getAdminKey());
        if(!adminEmail.equals(magicEmail)){
            throw new RuntimeException();
        }
    }

    public void verifySignInUserRequest(UserSignInReq signInReq) throws Exception {
        getEmailFromMagicToken(signInReq.getMagicToken());
    }

//    Admin Token
    public String generateAdminToken() {
        String secret = adminService.getSecret();
        Instant now = Instant.now();
        return JWT.create()
                .withIssuer(RandomStringUtils.randomAlphanumeric(6))
                .withIssuedAt(now)
                .withExpiresAt(now.plus(Constants.JWT.ADMIN_TOKEN_EXPIRE_AFTER_IN_SEC, ChronoUnit.SECONDS))
                .sign(Algorithm.HMAC256(secret));
    }

    @Transactional
    public void verifyAdminToken() throws Exception {
        String token = httpServletRequest.getHeader(Constants.HEADER.ADMIN_HEADER_NAME);
        String secret = adminService.getSecret();
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT.getExpiresAt().before(new Date())){
                throw new CredentialsExpiredException(ErrorMessages.EXPIRED_TOKEN);
            }
        } catch (Exception e){
            throw new Exception(ErrorMessages.INVALID_TOKEN);
        }
    }
}
