package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class HotelEntity {
    private int id;
    private int districts_id;
    private String name;
    private String map_link;
    private String website;
    private String review;
    private Timestamp created_at;
    private Timestamp updated_at;

    private DistrictEntity district;
}
