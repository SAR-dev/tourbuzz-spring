package com.backend.api.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BkashPaymentRes {
    private String paymentID;
    private String trxID;
    private String transactionStatus;
    private double amount;
    private String currency;
    private String intent;
    private LocalDateTime paymentExecuteTime;
    private String merchantInvoiceNumber;
    private String payerReference;
    private String customerMsisdn;
    private String statusCode;
    private String statusMessage;
}
