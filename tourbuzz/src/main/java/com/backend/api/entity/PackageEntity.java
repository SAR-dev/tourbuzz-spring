package com.backend.api.entity;

import com.backend.api.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PackageEntity {
    private int id;
    private int tours_id;
    private String title;
    private String description;
    private String additional_info;
    private int price;
    private Status status;
    private Timestamp created_at;
    private Timestamp updated_at;
}
