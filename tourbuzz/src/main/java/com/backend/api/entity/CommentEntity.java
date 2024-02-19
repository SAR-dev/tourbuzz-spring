package com.backend.api.entity;

import com.backend.api.enums.CommentRef;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class CommentEntity {
    private int id;
    private CommentRef reference_type;
    private int reference_id;
    private int parent_id;
    private int users_id;
    private String title;
    private String details;
    private boolean deleted;
    private Timestamp created_at;
    private Timestamp updated_at;

    private int child_count;
    private UserEntity author;
}
