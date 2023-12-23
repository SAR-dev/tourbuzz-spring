package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MagicLinkReq {
    @NotBlank(message = "Email address is required")
    @Email
    @JsonProperty("email")
    private String email;
}
