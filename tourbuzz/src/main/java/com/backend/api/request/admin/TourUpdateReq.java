package com.backend.api.request.admin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TourUpdateReq {
    @NotNull
    private @Valid TourUpdatePayload tour;

    @NotNull
    @Size(min = 1, max = 10)
    private List<@Valid TourLogUpdatePayload> tourLogs;

    @NotNull
    @Size(min = 1, max = 5)
    List<@Valid TourPackageUpdatePayload> packages;

}
