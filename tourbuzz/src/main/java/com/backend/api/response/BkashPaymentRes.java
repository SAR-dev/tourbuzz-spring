package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BkashPaymentRes {
    private String paymentID;
    private String trxID;
    private String transactionStatus;
    private double amount;
    private String currency;
    private LocalDateTime paymentExecuteTime;
    private String merchantInvoiceNumber;
    private String payerReference;
    private String customerMsisdn;
    private String statusCode;
    private String statusMessage;
}
