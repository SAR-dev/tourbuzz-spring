package com.backend.api.controller;

import com.backend.api.payload.MagicLinkReq;
import com.backend.api.payload.SignInReq;
import com.backend.api.response.AccessToken;
import com.backend.api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/magic-link")
    public void reqMagicLink(@Valid @RequestBody MagicLinkReq request) {
        authService.emailMagicLink(request.getEmail());
    }

    @PostMapping("/sign-in")
    public AccessToken signIn(@Valid @RequestBody SignInReq request) throws Exception {
        return authService.getAccessToken(request.getToken());
    }
}
