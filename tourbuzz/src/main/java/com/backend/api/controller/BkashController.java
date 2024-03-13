package com.backend.api.controller;

import com.backend.api.request.BkashPaymentCreateReq;
import com.backend.api.service.BkashService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bkash")
public class BkashController {
    private final BkashService bkashService;

    @Tag(name = "Get Payment Link")
    @PostMapping("/payment-link")
    public String getPaymentLink(@RequestBody BkashPaymentCreateReq paymentCreateReq) throws Exception {
        return bkashService.getPaymentLink(paymentCreateReq);
    }

    @Tag(name = "Payment Callback")
    @GetMapping("/callback")
    public ResponseEntity<?> handlePaymentCallback(
            @RequestParam String paymentID,
            @RequestParam String status
    ) throws Exception {
        if (!status.equals("success")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Payment was not successful. Status: " + status);
        }
        return bkashService.handlePaymentCallback(paymentID);
    }
}
