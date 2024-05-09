package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AdEntity {
    private int id;
    private String title;
    private String subtitle;
    private String link;
    private String imageUrl;
    private boolean active;
    private int viewCount;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
