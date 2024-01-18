package com.backend.api.request.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnquiryQuestionReq {
    @NotNull
    @Size(min = 10, max = 5000)
    private String question;

    @NotNull
    @Size(min = 7, max = 20)
    @JsonProperty("contact_no")
    private String contactNo;

    private String email;
}
