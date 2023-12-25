package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class OrganizerEntity {
    private int id;
    private String name;
    private String username;
    private String description;
    private String slogan;
    private String email;
    private String images_id;
    private String verification;
    private String social_link;
    private String contact_number;
    private Timestamp created_at;
    private Timestamp updated_at;

    private ImageEntity avatar;
}
