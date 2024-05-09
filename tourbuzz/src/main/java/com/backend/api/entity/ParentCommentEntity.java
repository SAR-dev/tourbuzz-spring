package com.backend.api.entity;

import com.backend.api.enums.CommentRef;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ParentCommentEntity {
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
    private List<ChildCommentEntity> children;
    private UserEntity author;

    public String getDetails() {
        if(deleted) return null;
        return details;
    }
}
