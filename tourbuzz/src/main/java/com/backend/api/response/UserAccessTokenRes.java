package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAccessTokenRes {
    private String accessToken;
    private String accessTokenHeader;
}
