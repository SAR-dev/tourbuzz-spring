package com.backend.api.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EmojiReactionEntity {
    private int id;
    private int reference_type;
    private int reference_id;
    private int users_id;
    private String emoji;
    private Timestamp created_at;
    private Timestamp updated_at;
}
