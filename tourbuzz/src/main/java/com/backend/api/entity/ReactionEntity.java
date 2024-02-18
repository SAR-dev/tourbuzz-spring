package com.backend.api.entity;

import com.backend.api.enums.ReactionRef;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReactionEntity {
    private int id;
    private ReactionRef reference_type;
    private int reference_id;
    private int users_id;
    private String emoji;
    private Timestamp created_at;
    private Timestamp updated_at;
}
