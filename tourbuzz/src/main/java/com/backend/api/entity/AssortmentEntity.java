package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssortmentEntity {
    private int id;
    private String title;
    private String details;
    private String code;
    private int imagesId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
