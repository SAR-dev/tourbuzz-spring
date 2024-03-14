package com.backend.api.controller.user;

import com.backend.api.request.InvoiceCreateReq;
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
    @GetMapping("/invoice")
    public String getBkashInvoiceNo(@RequestBody InvoiceCreateReq invoiceCreateReq){
        return bkashService.getBkashInvoiceNo(invoiceCreateReq);
    }

    @Tag(name = "Check Invoice Status")
    @GetMapping("/invoice/{invoiceNo}")
    public PaymentRes queryPaymentByInvoiceNo(@PathVariable String invoiceNo) throws Exception {
        return bkashService.queryPaymentByInvoiceNo(invoiceNo);
    }
}
