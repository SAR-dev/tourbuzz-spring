package com.backend.api.controller;

import com.backend.api.entity.UserEntity;
import com.backend.api.request.MagicLinkReq;
import com.backend.api.request.SignInReq;
import com.backend.api.response.AccessTokenRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @GetMapping("/me")
    public UserEntity getLoggedInUser() {
        String email = authService.getLoggedInEmail();
        return userService.getUserByEmail(email);
    }
}
