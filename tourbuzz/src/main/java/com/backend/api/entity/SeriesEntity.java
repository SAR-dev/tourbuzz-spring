package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SeriesEntity {
    private int id;
    private String name;
    private String slug;
    private int imagesId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private ImageEntity avatar;
}
