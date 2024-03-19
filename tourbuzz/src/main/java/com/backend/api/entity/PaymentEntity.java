package com.backend.api.entity;

import com.backend.api.enums.PaymentType;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class PaymentEntity {
    private int id;
    private int users_id;
    private String invoice_no;
    private String reference_no;
    private PaymentType payment_type;
    private String transaction_id;
    private String payment_id;
    private double payment_amount;
    private String currency;
    private boolean completed;
    private Timestamp created_at;
    private Timestamp updated_at;

    private UserEntity user;
}
