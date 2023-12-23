package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ImageEntity {
    private int id;
    private int users_id;
    private String title;
    private String path;
    private Timestamp created_at;
    private Timestamp updated_at;
}
