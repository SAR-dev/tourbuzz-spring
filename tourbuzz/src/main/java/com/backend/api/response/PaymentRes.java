package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentRes {
    private String paymentId;
    private String transactionId;
    private double paymentAmount;
    private String currency;
    private Timestamp paidAt;
    private String referenceNo;
    private boolean completed;
}
