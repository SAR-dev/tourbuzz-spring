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
public class FileUpdateReq {
    @NotNull
    private int filesId;

    @NotNull
    @Size(min = 3, max = 100)
    private String filename;

    @NotNull
    @Size(min = 3, max = 2000)
    private String link;

    @NotNull
    @Size(min = 3, max = 1000)
    private String note;
}
