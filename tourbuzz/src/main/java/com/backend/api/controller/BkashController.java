package com.backend.api.controller;

import com.backend.api.request.BkashPaymentCreateReq;
import com.backend.api.response.PaymentRes;
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
    public PaymentRes handlePaymentCallback(
            @RequestParam String paymentID,
            @RequestParam String status
    ) throws Exception {
        if (!status.equals("success")) {
            throw new Exception("Payment was not successful. Status: " + status);
        }
        return bkashService.handlePaymentCallback(paymentID);

//        Show success or error message here
    }
}
