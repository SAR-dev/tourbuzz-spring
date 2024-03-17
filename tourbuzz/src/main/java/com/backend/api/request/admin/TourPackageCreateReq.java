package com.backend.api.request.admin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TourPackageCreateReq {
    @NotNull
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

    @NotNull
    @Min(1)
    @Max(100000)
    private int threshold;
}
