package com.backend.api.request.admin;

import com.backend.api.Constants;
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
public class TourLogCreateReq {
    @NotNull
    @JsonProperty("tours_id")
    private int toursId;

    @JsonProperty("hotels_id")
    private Integer hotelsId;

    @NotNull
    @Size(min = 5, max = 100)
    private String title;

    @NotNull
    @Size(min = 10, max = 2000)
    private String description;

    @NotNull
    @Pattern(regexp = Constants.DATES.DD_MM_YYYY_REGEX, message = "Invalid date format. Use dd/MM/yyyy.")
    private String date;

    @NotNull
    @JsonProperty("place_ids")
    private List<Integer> placeIds;

    @NotNull
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
