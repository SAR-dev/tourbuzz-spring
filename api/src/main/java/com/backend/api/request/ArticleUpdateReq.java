package com.backend.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ArticleUpdateReq {
    private String title;
    private String slug;
    private String subtitle;
    private String summary;

    @JsonProperty("md_desc")
    private String mdDesc;
    @JsonProperty("image_ids")
    private List<Integer> imageIds;
    @JsonProperty("writers_id")
    private int writersId;
    @JsonProperty("articles_id")
    private int articlesId;

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
