package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class UserEntity {
    private int id;
    private String email;
    private String username;
    private String name;
    private int images_id;
    private Timestamp last_logged_in;
    private Timestamp created_at;
    private Timestamp updated_at;

    private ImageEntity avatar;
}
