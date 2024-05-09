package com.backend.api.response;

import com.backend.api.Constants;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserAccessTokenRes {
    private String accessToken;
    private String accessTokenHeader;

    public String getAccessToken() {
        return Constants.HEADER.ACCESS_HEADER_STARTS_WITH + accessToken;
    }
}
