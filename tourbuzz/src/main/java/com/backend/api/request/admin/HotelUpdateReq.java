package com.backend.api.request.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HotelUpdateReq {
    @NotNull
    private Integer hotelsId;

    @NotNull
    private Integer imagesId;

    @NotNull
    private Integer districtsId;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @JsonProperty("map_link")
    private String mapLink;

    @NotNull
    @Size(min = 10, max = 500)
    private String review;

    private String website;
}
