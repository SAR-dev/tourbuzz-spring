package com.backend.api.entity;

import com.backend.api.enums.CommentRef;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ChildCommentEntity {
    private int id;
    private CommentRef referenceType;
    private int referenceId;
    private int parentId;
    private int usersId;
    private String details;
    private boolean deleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private int childCount;
    private UserEntity author;

    public String getDetails() {
        if(deleted) return null;
        return details;
    }
}
