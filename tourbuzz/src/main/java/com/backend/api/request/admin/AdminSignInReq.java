package com.backend.api.request.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminSignInReq {
    @NotBlank
    @JsonProperty("magic_token")
    private String magicToken;

    @NotBlank
    @JsonProperty("admin_key")
    private String adminKey;
}
