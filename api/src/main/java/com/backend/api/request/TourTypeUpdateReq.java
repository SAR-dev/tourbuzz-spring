package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourTypeUpdateReq {
    @JsonProperty("tour_types_id")
    private int tourTypesId;
    private String name;
    private String slug;
    private String details;
}
