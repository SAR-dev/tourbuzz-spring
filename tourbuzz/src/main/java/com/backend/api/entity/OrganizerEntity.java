package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrganizerEntity {
    private int id;
    private String name;
    private String username;
    private String description;
    private String slogan;
    private String email;
    private int imagesId;
    private String verification;
    private String socialLink;
    private String contactNo;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private ImageEntity avatar;
    private List<UserEntity> admins;
    private List<UserEntity> staffs;
}
