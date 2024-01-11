package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInReq {
    @NotBlank
    @JsonProperty("magic_token")
    private String magicToken;

    @NotBlank
    @JsonProperty("admin_token")
    private String adminToken;
}
