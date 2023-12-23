package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInReq {
    @NotBlank(message = "Tokens is required")
    @Email
    @JsonProperty("token")
    private String token;
}
