package com.backend.api.response;

import lombok.Data;

@Data
public class ReactionRes {
    private String emoji;
    private int count;
    private boolean reacted;
}
