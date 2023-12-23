package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class WriterEntity {
    private int id;
    private String email;
    private String avatar;
    private String name;
    private Timestamp created_at;
    private Timestamp updated_at;
}
