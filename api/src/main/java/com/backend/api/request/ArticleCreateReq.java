package com.backend.api.request;

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
    @Size(min = 10, max = 100)
    private String title;
    @NotNull
    @Size(min = 10, max = 100)
    private String slug;
    @NotNull
    @Size(min = 10, max = 200)
    private String subtitle;
    @NotNull
    @Size(min = 10, max = 400)
    private String summary;

    @NotNull
    @Size(min = 10, max = 20000)
    @JsonProperty("md_desc")
    private String mdDesc;

    @JsonProperty("image_ids")
    private List<Integer> imageIds;

    @JsonProperty("writers_id")
    private int writersId;

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
