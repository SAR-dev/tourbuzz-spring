package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ImageEntity {
    private int id;
    private String title;
    private String source_url;
    private String thumbnail_url;
    private String optimized_url;
    private Timestamp created_at;
    private Timestamp updated_at;
}
