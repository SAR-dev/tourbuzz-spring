package com.backend.api.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CategoryEntity {
    private int id;
    private String name;
    private String slug;
    private int images_id;
    private Timestamp created_at;
    private Timestamp updated_at;

    private ImageEntity avatar;
}
