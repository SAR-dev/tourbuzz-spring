package com.backend.api.controller.user;

import com.backend.api.request.InvoiceCreateReq;
import com.backend.api.response.InvoicePaymentRes;
import com.backend.api.response.PaymentRes;
import com.backend.api.service.BkashService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/bkash")
public class UserBkashController {
    private final BkashService bkashService;

    @Tag(name = "Get Invoice No")
    @PostMapping("/invoice")
    public InvoicePaymentRes getBkashInvoiceNo(@RequestBody InvoiceCreateReq invoiceCreateReq) throws Exception {
        return bkashService.getInvoicePaymentRes(invoiceCreateReq);
    }

    @Tag(name = "Check Invoice Status")
    @GetMapping("/invoice/{invoiceNo}")
    public PaymentRes queryPaymentByInvoiceNo(@PathVariable String invoiceNo) throws Exception {
        return bkashService.queryPaymentByInvoiceNo(invoiceNo);
    }
}
