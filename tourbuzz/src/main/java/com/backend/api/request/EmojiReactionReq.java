package com.backend.api.request;

import com.backend.api.enums.EmojiReferenceType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmojiReactionReq {
    private String referenceType;
    private int referenceId;
    private String emoji;
}
