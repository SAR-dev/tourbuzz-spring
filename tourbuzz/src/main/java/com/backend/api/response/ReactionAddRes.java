package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReactionAddRes {
    private boolean added;
    private boolean removed;
}
