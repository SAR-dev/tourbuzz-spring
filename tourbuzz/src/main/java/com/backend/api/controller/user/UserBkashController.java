package com.backend.api.controller.user;

import com.backend.api.ErrorMessages;
import com.backend.api.request.PaymentCreateReq;
import com.backend.api.response.PaymentRes;
import com.backend.api.service.BkashService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/bkash")
public class UserBkashController {
    private final BkashService bkashService;

    @Tag(name = "Get Payment Link")
    @PostMapping("/payment-link")
    public String getPaymentLink(@RequestBody PaymentCreateReq paymentCreateReq) throws Exception {
        return bkashService.getPaymentLink(paymentCreateReq);
    }

    @Tag(name = "Check Invoice Status")
    @GetMapping("/invoice/{invoiceNo}")
    public void queryPaymentByInvoiceNo(@PathVariable String invoiceNo) throws Exception {
        PaymentRes paymentRes = bkashService.queryPaymentByInvoiceNo(invoiceNo);
        if(!paymentRes.isCompleted()){
            throw new Exception(ErrorMessages.PAYMENT_NOT_COMPLETED);
        }
    }
}
