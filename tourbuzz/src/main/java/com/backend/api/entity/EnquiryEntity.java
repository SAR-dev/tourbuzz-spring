package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EnquiryEntity {
    private int id;
    private int usersId;
    private String question;
    private String answer;
    private Timestamp answeredAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private UserEntity user;
}
