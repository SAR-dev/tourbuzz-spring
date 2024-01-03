package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserUpdateReq {
    private String email;
    private String name;
    @JsonProperty("images_id")
    private int imagesId;
}
