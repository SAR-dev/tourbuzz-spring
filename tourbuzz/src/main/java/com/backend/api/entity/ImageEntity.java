package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ImageEntity {
    private int id;
    private String title;
    private String sourceUrl;
    private String thumbnailUrl;
    private String optimizedUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
