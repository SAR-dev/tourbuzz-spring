package com.backend.api.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PurchaseEntity {
    private int id;
    private int products_id;
    private int users_id;
    private int product_amount;
    private String note;
    private Timestamp created_at;
    private Timestamp updated_at;
}
