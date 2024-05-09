package com.backend.api.response.admin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AdminAccessTokenRes {
    private String accessToken;
    private String accessTokenHeader;

    private String adminToken;
    private String adminTokenHeader;
}
