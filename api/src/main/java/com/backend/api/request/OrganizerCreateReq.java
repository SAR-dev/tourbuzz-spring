package com.backend.api.request;

import com.backend.api.enums.Levels;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizerCreateReq {
    @JsonProperty("images_id")
    private int imagesId;

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
    @Size(min = 10, max = 50)
    private String slogan;

    @NotNull
    @Size(min = 7, max = 20)
    @JsonProperty("contact_no")
    private String contactNo;

    @NotNull
    @Size(min = 4, max = 20)
    private Levels verification;

    @JsonProperty("social_link")
    private String socialLink;
}
