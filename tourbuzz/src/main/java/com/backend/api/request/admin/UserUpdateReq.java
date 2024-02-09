package com.backend.api.request.admin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserUpdateReq {
    @Size(min = 2, max = 25)
    private String name;

    private Integer imagesId;

    private String contactNo;

    private String contactEmail;

    private String residence;

    private String facebookId;

    private String youtubeId;

    private String twitterId;
}
