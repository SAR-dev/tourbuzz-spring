package com.backend.api.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefaultResponse {
    private boolean completed;
}
