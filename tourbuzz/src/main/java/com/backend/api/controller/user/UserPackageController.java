package com.backend.api.controller.user;

import com.backend.api.request.BookCreateReq;
import com.backend.api.request.PaymentCreateReq;
import com.backend.api.service.PackageService;
import com.backend.api.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/packages")
public class UserPackageController {
    private final PackageService packageService;
    private final PaymentService paymentService;

    @Tag(name = "Book Invoice No")
    @PostMapping("/book")
    public void bookBkashInvoiceNo(@RequestBody BookCreateReq bookCreateReq) throws Exception {
        paymentService.bookInvoiceNo(bookCreateReq);
    }
}
