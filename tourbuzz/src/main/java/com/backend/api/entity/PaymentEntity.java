package com.backend.api.entity;

import com.backend.api.enums.PaymentType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentEntity {
    private int id;
    private int usersId;
    private String invoiceNo;
    private String referenceNo;
    private PaymentType paymentType;
    private String transactionId;
    private String paymentId;
    private double paymentAmount;
    private String currency;
    private boolean completed;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private UserEntity user;
}
