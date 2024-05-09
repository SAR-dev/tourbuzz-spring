package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PurchaseEntity {
    private int id;
    private int productsId;
    private int usersId;
    private int productAmount;
    private String note;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
