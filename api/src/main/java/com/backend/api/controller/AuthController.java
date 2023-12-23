package com.backend.api.controller;

import com.backend.api.payload.SignUpReq;
import com.backend.api.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody SignUpReq request) {
        authService.emailMagicLink(request.getEmail());
    }
}
