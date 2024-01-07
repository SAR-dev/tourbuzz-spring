package com.backend.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourTypeCreateReq {
    @NotNull
    private String name;
    @NotNull
    private String slug;
    @NotNull
    private String details;
}
