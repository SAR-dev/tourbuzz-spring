package com.backend.api.request.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceUpdateReq {
    @NotNull
    @JsonProperty("places_id")
    private Integer placesId;

    @NotNull
    @JsonProperty("images_id")
    private Integer imagesId;

    @NotNull
    @JsonProperty("districts_id")
    private Integer districtsId;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @JsonProperty("postal_code")
    private String postalCode;

    @NotNull
    @JsonProperty("map_link")
    private String mapLink;

    @NotNull
    @Size(min = 10, max = 1000)
    private String description;

    @NotNull
    @Size(max = 20)
    private String type;
}
