package com.backend.api.request;

import com.backend.api.Constants;
import com.backend.api.ErrorMessages;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class TourUpdatePayload {
    @NotNull
    private int id;

    @NotNull
    @JsonProperty("tour_types_id")
    private int tourTypesId;

    @NotNull
    @JsonProperty("organizers_id")
    private int organizersId;

    @NotNull
    @JsonProperty("images_id")
    private int imagesId;

    @NotNull
    @Size(min = 10, max = 100)
    private String title;

    @NotNull
    @Size(min = 10, max = 100)
    private String slug;

    @NotNull
    @Size(min = 10, max = 50000)
    @JsonProperty("md_desc")
    private String mdDesc;

    @NotNull
    @Size(min = 10, max = 5000)
    private String summary;

    @NotNull
    @Size(min = 1, max = 20)
    @JsonProperty("group_size")
    private String groupSize;

    @NotNull
    @Size(min = 1, max = 100)
    @JsonProperty("group_info")
    private String groupInfo;

    @NotNull
    @Size(min = 10, max = 2000)
    private String services;

    @NotNull
    @Size(min = 5, max = 200)
    private String transports;

    @NotNull
    @Size(min = 10, max = 5000)
    @JsonProperty("cancel_ins")
    private String cancelIns;

    @NotNull
    @Size(min = 10, max = 5000)
    @JsonProperty("start_ins")
    private String startIns;

    @NotNull
    @Size(min = 10, max = 5000)
    @JsonProperty("finish_ins")
    private String finishIns;

    @NotNull
    @Pattern(regexp = Constants.DD_MM_YYYY_HH_MM_REGEX, message = "Invalid date format. Use dd/MM/yyyy hh:mm.")
    @JsonProperty("start_at")
    private String startAt;

    @NotNull
    @Pattern(regexp = Constants.DD_MM_YYYY_HH_MM_REGEX, message = "Invalid date format. Use dd/MM/yyyy hh:mm.")
    @JsonProperty("finish_at")
    private String finishAt;

    @NotNull
    @Pattern(regexp = Constants.DD_MM_YYYY_HH_MM_REGEX, message = "Invalid date format. Use dd/MM/yyyy hh:mm.")
    @JsonProperty("last_sell_at")
    private String lastSellAt;

    @NotNull
    @Size(min = 1, max = 10)
    @JsonProperty("image_ids")
    private List<Integer> imageIds;

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
