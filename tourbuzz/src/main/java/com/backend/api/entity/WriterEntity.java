package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WriterEntity {
    private int id;
    private int imagesId;
    private String email;
    private String name;
    private String username;
    private String contact_no;
    private String website;
    private String about;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private ImageEntity avatar;
}
