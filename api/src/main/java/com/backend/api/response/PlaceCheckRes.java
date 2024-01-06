package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceCheckRes {
    private boolean name_exists;
}
