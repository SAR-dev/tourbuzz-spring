package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnquiryAnswerReq {
    @NotNull
    @Size(min = 10, max = 5000)
    private String answer;

    @NotNull
    @JsonProperty("enquiries_id")
    private Integer enquiriesId;
}
