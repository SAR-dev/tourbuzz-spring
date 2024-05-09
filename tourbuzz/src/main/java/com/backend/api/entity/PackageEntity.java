package com.backend.api.entity;

import com.backend.api.enums.Status;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PackageEntity {
    private int id;
    private int toursId;
    private String title;
    private String included;
    private String excluded;
    private String conditions;
    private int price;
    private Status status;
    private int threshold;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
