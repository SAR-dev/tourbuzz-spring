package com.backend.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentUpdateReq {
    @NotBlank
    @NotNull
    private int id;

    @Size(min = 2, max = 5000)
    private String details;
}
