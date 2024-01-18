package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdUpdateReq {
    @NotNull
    @JsonProperty("ads_id")
    private Integer adsId;

    @NotNull
    @Size(min = 1, max = 100)
    private String title;

    @NotNull
    @Size(min = 10, max = 300)
    private String subtitle;

    @NotNull
    private String link;

    @NotNull
    @JsonProperty("image_url")
    private String imageUrl;

    @NotNull
    private boolean active;
}
