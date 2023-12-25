package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class EnquiryEntity {
    private int id;
    private int users_id;
    private String contact_number;
    private String message;
    private String answer;
    private Timestamp created_at;
    private Timestamp updated_at;

    private UserEntity sender;
}
