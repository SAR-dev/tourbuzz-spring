package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourTypeCheckRes {
    private boolean name_exists;
    private boolean slug_exists;
}
