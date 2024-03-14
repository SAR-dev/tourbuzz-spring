package com.backend.api.controller.user;

import com.backend.api.service.BkashService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/bkash")
public class UserBkashController {
    private final BkashService bkashService;

    @Tag(name = "Get Invoice No")
    @GetMapping("/invoice")
    public String getBkashInvoiceNo(){
        return bkashService.getBkashInvoiceNo();
    }

    @Tag(name = "Check Invoice Status")
    @GetMapping("/invoice/{invoiceNo}")
    public ResponseEntity<?> queryPaymentByInvoiceNo(@PathVariable String invoiceNo) throws Exception {
        return bkashService.queryPaymentByInvoiceNo(invoiceNo);
    }
}
