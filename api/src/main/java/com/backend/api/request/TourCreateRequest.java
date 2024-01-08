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
public class TourCreateRequest {
    @NotNull
    private @Valid TourCreatePayload tour;

    @NotNull
    @Size(min = 1, max = 10)
    @JsonProperty("tour_logs")
    private List<@Valid TourLogCreatePayload> tourLogs;

    @NotNull
    @Size(min = 1, max = 10)
    List<@Valid TourPackageCreatePayload> packages;

}
