package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourTypeUpdateReq {
    @NotNull
    @JsonProperty("tour_types_id")
    private int tourTypesId;

    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    @NotNull
    @Size(min = 3, max = 20)
    private String slug;
    @NotNull
    @Size(min = 5, max = 100)
    private String details;
}
