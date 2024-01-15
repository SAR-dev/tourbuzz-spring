package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TourUpdateReq {
    @NotNull
    private @Valid TourUpdatePayload tour;

    @NotNull
    @Size(min = 1, max = 10)
    @JsonProperty("tour_logs")
    private List<@Valid TourLogUpdatePayload> tourLogs;

    @NotNull
    @Size(min = 1, max = 5)
    List<@Valid TourPackageUpdatePayload> packages;

}
