package com.backend.api.controller;

import com.backend.api.entity.UserEntity;
import com.backend.api.request.admin.UserUpdateReq;
import com.backend.api.service.AuthService;
import com.backend.api.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @Tag(name = "Get Self Data")
    @GetMapping("/me")
    public UserEntity getLoggedInUser() {
        String email = authService.getLoggedInEmail();
        return userService.getUserByEmail(email);
    }

    @Tag(name = "Update Self Data")
    @PutMapping("/me")
    public void updateLoggedInUser(@RequestBody UserUpdateReq userUpdateReq) throws Exception {
        String email = authService.getLoggedInEmail();
        userService.updateUserByEmail(userUpdateReq, email);
    }
}
