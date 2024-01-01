package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnquiryQuestionReq {
    private String question;
    @JsonProperty("contact_number")
    private String contactNumber;

    private String email;
}
