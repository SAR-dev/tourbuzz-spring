package com.backend.api.controller;

import com.backend.api.Constants;
import com.backend.api.request.MagicLinkReq;
import com.backend.api.request.SignInReq;
import com.backend.api.response.AdminAccessTokenRes;
import com.backend.api.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Tag(name = "Get Admin Token")
    @GetMapping("/admin-login-token")
    public String getAdminToken() throws Exception {
        return authService.generateAdminToken();
    }

    @Tag(name = "Get Magic Link")
    @PostMapping("/magic-link")
    public void reqMagicLink(@RequestBody MagicLinkReq request) throws MessagingException {
        authService.emailMagicLink(request.getEmail());
    }

    @Tag(name = "Admin Sign In")
    @PostMapping("/admin-sign-in")
    public AdminAccessTokenRes signIn(@RequestBody SignInReq request) throws Exception {
        authService.verifyAdminToken(request.getAdminToken());
        String accessToken = authService.getAccessToken(request.getMagicToken());
        String adminToken = request.getAdminToken();
        return AdminAccessTokenRes
                .builder()
                .accessToken(accessToken)
                .accessTokenHeader(Constants.ACCESS_HEADER_NAME)
                .adminToken(adminToken)
                .adminTokenHeader(Constants.ADMIN_HEADER_NAME)
                .build();
    }
}
