package com.backend.api.request.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ArticleCreateReq {
    @NotNull
    @JsonProperty("writers_id")
    private int writersId;

    @NotNull
    @Size(min = 10, max = 100)
    private String title;

    @NotNull
    @Size(min = 10, max = 100)
    private String slug;

    @NotNull
    @Size(min = 10, max = 200)
    private String subtitle;

    @NotNull
    @Size(min = 10, max = 600)
    private String summary;

    @NotNull
    @Size(min = 10, max = 20000)
    @JsonProperty("md_desc")
    private String mdDesc;

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
