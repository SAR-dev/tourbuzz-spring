package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PlaceEntity {
    private int id;
    private int imagesId;
    private int districtsId;
    private String name;
    private String type;
    private String postalCode;
    private String mapLink;
    private String description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private DistrictEntity district;
    private ImageEntity avatar;
}
