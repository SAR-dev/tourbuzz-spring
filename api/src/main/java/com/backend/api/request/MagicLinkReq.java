package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MagicLinkReq {
    @JsonProperty("email")
    private String email;
}
