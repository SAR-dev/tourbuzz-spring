package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnquiryAnswerReq {
    private String answer;
    @JsonProperty("enquiries_id")
    private int enquiriesId;
}
