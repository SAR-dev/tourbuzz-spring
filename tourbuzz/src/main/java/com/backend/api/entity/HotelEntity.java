package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class HotelEntity {
    private int id;
    private int districts_id;
    private int images_id;
    private String name;
    private String map_link;
    private String website;
    private String review;
    private Timestamp created_at;
    private Timestamp updated_at;

    private DistrictEntity district;
    private ImageEntity avatar;
    private List<UserEntity> admins;
    private List<UserEntity> staffs;
}
