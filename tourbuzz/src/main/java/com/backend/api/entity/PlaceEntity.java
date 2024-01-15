package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PlaceEntity {
    private int id;
    private int images_id;
    private int districts_id;
    private String name;
    private String type;
    private String postal_code;
    private String map_link;
    private String description;
    private Timestamp created_at;
    private Timestamp updated_at;

    private DistrictEntity district;
    private ImageEntity avatar;
}
