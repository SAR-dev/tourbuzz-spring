package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MagicLinkReq {
    @NotNull
    @Email
    @JsonProperty("email")
    private String email;
}
