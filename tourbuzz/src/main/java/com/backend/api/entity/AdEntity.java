package com.backend.api.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AdEntity {
    private int id;
    private String title;
    private String subtitle;
    private String link;
    private String image_url;
    private boolean active;
    private int view_count;
    private Timestamp created_at;
    private Timestamp updated_at;
}
