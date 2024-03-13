package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.enums.PaymentType;
import com.backend.api.repository.postgres.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AuthService authService;

    public String getInvoiceNo(PaymentType paymentType){
        String email = authService.getLoggedInEmail();
        return paymentRepository.getInvoiceNo(email, paymentType.name());
    }

    public void registerPaymentIdWithInvoice(String invoiceNo, String paymentId) throws Exception {
        boolean updated = paymentRepository.registerPaymentIdWithInvoice(invoiceNo, paymentId) == 1;
        if(!updated){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }
}
