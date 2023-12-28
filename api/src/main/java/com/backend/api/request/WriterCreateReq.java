package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WriterCreateReq {
    @JsonProperty("images_id")
    private int imagesId;
    private String email;
    private String name;
    private String username;
    private String about;
    @JsonProperty("phone_no")
    private String phoneNo;
    private String website;
}
