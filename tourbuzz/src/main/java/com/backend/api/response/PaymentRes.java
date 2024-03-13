package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentRes {
    private String paymentId;
    private String transactionId;
    private double paymentAmount;
    private String currency;
    private LocalDateTime paidAt;
    private String referenceNo;
}
