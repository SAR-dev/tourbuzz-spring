package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateReq {
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 2, max = 25)
    private String name;
    @JsonProperty("images_id")
    private int imagesId;
}
