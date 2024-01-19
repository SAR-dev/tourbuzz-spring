package com.backend.api.response.admin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminAccessTokenRes {
    private String accessToken;
    private String accessTokenHeader;

    private String adminToken;
    private String adminTokenHeader;
}
