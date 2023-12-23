package com.backend.api.controller;

import com.backend.api.request.MagicLinkReq;
import com.backend.api.request.SignInReq;
import com.backend.api.response.AccessTokenRes;
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
    public AccessTokenRes signIn(@Valid @RequestBody SignInReq request) throws Exception {
        return authService.getAccessToken(request.getToken());
    }
}
