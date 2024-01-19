package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserSignInReq {
    @NotBlank
    @NotNull
    @JsonProperty("magic_token")
    private String magicToken;
}
