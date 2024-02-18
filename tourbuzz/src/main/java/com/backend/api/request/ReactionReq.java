package com.backend.api.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReactionReq {
    private String referenceType;
    private int referenceId;
    private String emoji;
}
