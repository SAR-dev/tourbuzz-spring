package com.backend.api.request.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdminSignInReq {
    @NotBlank
    @NotNull
    @JsonProperty("magic_token")
    private String magicToken;

    @NotBlank
    @NotNull
    @JsonProperty("admin_key")
    private String adminKey;
}
