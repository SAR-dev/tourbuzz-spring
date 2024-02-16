package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class EnquiryEntity {
    private int id;
    private int users_id;
    private String question;
    private String answer;
    private Timestamp answered_at;
    private Timestamp created_at;
    private Timestamp updated_at;

    private UserEntity user;
}
