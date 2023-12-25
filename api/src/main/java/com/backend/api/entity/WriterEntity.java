package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class WriterEntity {
    private int id;
    private int images_id;
    private String email;
    private String name;
    private String username;
    private String about;
    private Timestamp created_at;
    private Timestamp updated_at;

    private ImageEntity avatar;
}
