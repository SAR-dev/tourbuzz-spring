package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourPackageCreateReq {
    @NotNull
    @JsonProperty("tours_id")
    private int toursId;

    @NotNull
    @Size(min = 1, max = 20)
    private String title;

    @NotNull
    @Size(min = 5, max = 10000)
    private String included;

    @NotNull
    @Size(min = 5, max = 10000)
    private String excluded;

    @NotNull
    @Size(min = 5, max = 10000)
    private String conditions;

    @NotNull
    @Min(100)
    @Max(100000)
    private int price;
}
