package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelCreateReq {
    @NotNull
    @JsonProperty("districts_id")
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
