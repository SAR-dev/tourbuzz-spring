package com.backend.api.request.admin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PlaceCreateReq {
    @NotNull
    private Integer imagesId;

    @NotNull
    private Integer districtsId;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    private String postalCode;

    @NotNull
    private String mapLink;

    @NotNull
    @Size(min = 10, max = 1000)
    private String description;

    @NotNull
    @Size(max = 20)
    private String type;
}
