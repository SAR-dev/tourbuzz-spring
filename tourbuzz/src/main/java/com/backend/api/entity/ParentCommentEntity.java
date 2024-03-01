package com.backend.api.entity;

import com.backend.api.enums.CommentRef;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ParentCommentEntity {
    private int id;
    private CommentRef reference_type;
    private int reference_id;
    private int parent_id;
    private int users_id;
    private String details;
    private boolean deleted;
    private Timestamp created_at;
    private Timestamp updated_at;

    private int child_count;
    private List<ChildCommentEntity> children;
    private UserEntity author;

    public String getDetails() {
        if(deleted) return null;
        return details;
    }
}
