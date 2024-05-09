package com.backend.api.entity;

import com.backend.api.enums.ReactionRef;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReactionEntity {
    private int id;
    private ReactionRef reactionRef;
    private int referenceId;
    private int usersId;
    private String emoji;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
