package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelCheckRes {
    private boolean name_exists;
}
