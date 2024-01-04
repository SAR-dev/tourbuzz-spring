package com.backend.api.request;

import com.backend.api.enums.Levels;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrganizerCreateReq {
    @JsonProperty("images_id")
    private int imagesId;
    private String email;
    private String name;
    private String username;
    private String description;
    private String slogan;
    @JsonProperty("contact_no")
    private String contactNo;
    private Levels verification;
    @JsonProperty("social_link")
    private String socialLink;
}
