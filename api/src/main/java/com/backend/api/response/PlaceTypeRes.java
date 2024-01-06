package com.backend.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlaceTypeRes {
    private String type;
    private int count;
}
