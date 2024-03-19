package com.backend.api.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FileEntity {
    private int id;
    private String filename;
    private String link;
    private String note;
    private Timestamp created_at;
    private Timestamp updated_at;
}
