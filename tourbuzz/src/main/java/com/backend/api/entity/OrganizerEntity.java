package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OrganizerEntity {
    private int id;
    private String name;
    private String username;
    private String description;
    private String slogan;
    private String email;
    private int images_id;
    private String verification;
    private String social_link;
    private String contact_no;
    private Timestamp created_at;
    private Timestamp updated_at;

    private ImageEntity avatar;
    private List<UserEntity> admins;
    private List<UserEntity> staffs;
}
