package com.backend.api.request.admin;

import com.backend.api.Constants;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TourCreatePayload {
    @NotNull
    private int tourTypesId;

    @NotNull
    private int organizersId;

    @NotNull
    private int imagesId;

    @NotNull
    @Size(min = 10, max = 100)
    private String title;

    @NotNull
    @Size(min = 10, max = 100)
    private String slug;

    private String videoLink;

    @NotNull
    @Size(min = 10, max = 50000)
    private String mdDesc;

    @NotNull
    @Size(min = 10, max = 5000)
    private String highlights;

    @NotNull
    @Size(min = 1, max = 20)
    private String groupSize;

    @NotNull
    @Size(min = 1, max = 100)
    private String groupInfo;

    @NotNull
    @Size(min = 10, max = 10000)
    private String faq;

    @NotNull
    @Size(min = 3, max = 200)
    private String transports;

    @NotNull
    @Size(min = 10, max = 5000)
    private String cancelIns;

    @NotNull
    @Size(min = 10, max = 5000)
    private String startIns;

    @NotNull
    @Size(min = 10, max = 5000)
    private String finishIns;

    @NotNull
    @Pattern(regexp = Constants.DATES.DD_MM_YYYY_HH_MM_REGEX, message = "Invalid date format. Use dd/MM/yyyy hh:mm.")
    private String startAt;

    @NotNull
    @Pattern(regexp = Constants.DATES.DD_MM_YYYY_HH_MM_REGEX, message = "Invalid date format. Use dd/MM/yyyy hh:mm.")
    private String finishAt;

    @NotNull
    @Pattern(regexp = Constants.DATES.DD_MM_YYYY_HH_MM_REGEX, message = "Invalid date format. Use dd/MM/yyyy hh:mm.")
    private String lastSellAt;

    @NotNull
    @Size(min = 1, max = 10)
    private List<Integer> imageIds;

    @NotNull
    @Min(1)
    @Max(9999)
    private Integer maxTicketAmount;

    private String imageIdsStr;
    public String getImageIdsStr() {
        if(imageIds.isEmpty()){
            return null;
        } else {
            return imageIds.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
        }
    }
}
