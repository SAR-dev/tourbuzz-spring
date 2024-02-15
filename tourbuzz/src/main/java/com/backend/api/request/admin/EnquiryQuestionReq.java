package com.backend.api.request.admin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EnquiryQuestionReq {
    @NotNull
    @Size(min = 10, max = 5000)
    private String question;

    @NotNull
    @Size(min = 7, max = 20)
    private String contactNo;
}
