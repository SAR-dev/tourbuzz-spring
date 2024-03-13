package com.backend.api.service;

import com.backend.api.enums.PaymentType;
import com.backend.api.repository.postgres.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final AuthService authService;

    public String getReferenceId(PaymentType paymentType){
        String email = authService.getLoggedInEmail();
        return paymentRepository.getReferenceId(email, paymentType.name());
    }
}
