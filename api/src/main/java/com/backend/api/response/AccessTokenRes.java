package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccessTokenRes {
    private String accessToken;
    private String tokenType;
}
