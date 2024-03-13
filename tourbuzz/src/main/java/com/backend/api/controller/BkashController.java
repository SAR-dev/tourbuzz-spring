package com.backend.api.controller;

import com.backend.api.entity.UserEntity;
import com.backend.api.service.BkashService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bkash")
public class BkashController {
    private final BkashService bkashService;

    @Tag(name = "Get Payment Link")
    @GetMapping("")
    public String getPaymentLink() {
        return bkashService.getIdToken();
    }
}
