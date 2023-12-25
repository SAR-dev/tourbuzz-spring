package com.backend.api.controller;

import com.backend.api.request.MagicLinkReq;
import com.backend.api.request.SignInReq;
import com.backend.api.response.AccessTokenRes;
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

    @Tag(name = "Get Magic Link")
    @PostMapping("/magic-link")
    public void reqMagicLink(@RequestBody MagicLinkReq request) throws MessagingException {
        authService.emailMagicLink(request.getEmail());
    }

    @Tag(name = "Sign In")
    @PostMapping("/sign-in")
    public AccessTokenRes signIn(@RequestBody SignInReq request) throws Exception {
        return authService.getAccessToken(request.getToken());
    }
}
