package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PlaceEntity {
    private int id;
    private int images_id;
    private String name;
    private String division;
    private String district;
    private String map_link;
    private String description;
    private Timestamp created_at;
    private Timestamp updated_at;

    private ImageEntity thumbnail;
}
