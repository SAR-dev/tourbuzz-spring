package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HotelEntity {
    private int id;
    private int districtsId;
    private int imagesId;
    private String name;
    private String mapLink;
    private String website;
    private String review;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private DistrictEntity district;
    private ImageEntity avatar;
    private List<UserEntity> admins;
    private List<UserEntity> staffs;
}
