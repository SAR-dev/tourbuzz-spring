package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TourTypeEntity {
    private int id;
    private String name;
    private String slug;
    private String details;
    private Timestamp created_at;
    private Timestamp updated_at;
}
