package com.backend.api.request.admin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WriterUpdateReq {
    @NotNull
    private Integer writersId;

    private Integer imagesId;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 2, max = 25)
    private String name;

    @NotNull
    @Size(min = 2, max = 25)
    private String username;

    @NotNull
    @Size(min = 10, max = 500)
    private String about;

    @NotNull
    @Size(min = 7, max = 20)
    private String contactNo;

    private String website;
}
