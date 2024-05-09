package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TourTypeEntity {
    private int id;
    private String name;
    private String slug;
    private String details;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
