package com.backend.api.request.admin;

import com.backend.api.enums.ManagerPosition;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OrganizerManagerDeleteReq {
    @NotNull
    private String email;

    @NotNull
    @Min(1)
    private int organizersId;
}
