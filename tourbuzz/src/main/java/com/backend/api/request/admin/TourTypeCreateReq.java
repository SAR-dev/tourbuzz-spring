package com.backend.api.request.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourTypeCreateReq {
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    @Size(min = 3, max = 20)
    private String slug;
    @NotNull
    @Size(min = 5, max = 100)
    private String details;
}
