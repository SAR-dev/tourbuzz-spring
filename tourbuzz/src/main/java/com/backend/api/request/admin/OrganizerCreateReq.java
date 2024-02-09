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
public class OrganizerCreateReq {
    @NotNull
    private Integer imagesId;

    @NotNull
    @Size(min = 10, max = 50)
    private String email;

    @NotNull
    @Size(min = 2, max = 25)
    private String name;

    @NotNull
    @Size(min = 2, max = 25)
    private String username;

    @NotNull
    @Size(min = 10, max = 500)
    private String description;

    @NotNull
    @Size(min = 10, max = 100)
    private String slogan;

    @NotNull
    @Size(min = 7, max = 20)
    private String contactNo;

    @NotNull
    @Size(min = 4, max = 20)
    private String verification;

    private String socialLink;
}
