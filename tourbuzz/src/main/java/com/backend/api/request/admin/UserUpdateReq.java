package com.backend.api.request.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateReq {
    @Size(min = 2, max = 25)
    private String name;

    @JsonProperty("images_id")
    private Integer imagesId;

    @JsonProperty("contact_no")
    private String contactNo;

    @JsonProperty("contact_email")
    private String contactEmail;

    private String residence;

    @JsonProperty("facebook_id")
    private String facebookId;

    @JsonProperty("youtube_id")
    private String youtubeId;

    @JsonProperty("twitter_id")
    private String twitterId;
}
