package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FileEntity {
    private int id;
    private String filename;
    private String link;
    private String note;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
