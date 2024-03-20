package com.backend.api.controller;

import com.backend.api.Constants;
import com.backend.api.response.PaymentRes;
import com.backend.api.service.BkashService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bkash")
public class BkashController {
    private final BkashService bkashService;
    private final TemplateEngine templateEngine;

    @Tag(name = "Payment Callback")
    @GetMapping("/callback")
    public String handlePaymentCallback(
            @RequestParam String paymentID,
            @RequestParam String status
    ) {
        if (!status.equals("success")) {
            String templateName = Constants.TEMPLATES.PAYMENT_FAILED;
            Context context = new Context();
            context.setVariable(Constants.TEMPLATES.VARIABLE_NAME, null);
            return templateEngine.process(templateName, context);
        }
        try {
            PaymentRes paymentRes = bkashService.handlePaymentCallback(paymentID);
            String templateName;
            Context context = new Context();
            if(paymentRes.isCompleted()) {
                templateName = Constants.TEMPLATES.PAYMENT_SUCCESS;
                context.setVariable(Constants.TEMPLATES.VARIABLE_NAME, paymentRes);
            } else {
                templateName = Constants.TEMPLATES.PAYMENT_FAILED;
                context.setVariable(Constants.TEMPLATES.VARIABLE_NAME, null);
            }
            return templateEngine.process(templateName, context);
        }catch (Exception e){
            String templateName = Constants.TEMPLATES.PAYMENT_FAILED;
            Context context = new Context();
            context.setVariable(Constants.TEMPLATES.VARIABLE_NAME, null);
            return templateEngine.process(templateName, context);
        }
    }
}
