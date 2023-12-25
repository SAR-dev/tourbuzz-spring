package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CommentEntity {
    private int id;
    private int tours_id;
    private int users_id;
    private String title;
    private String description;
    private Timestamp created_at;
    private Timestamp updated_at;
}
