package com.backend.api.request.admin;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ArticleUpdateReq {
    @NotNull
    private Integer writersId;

    @NotNull
    private Integer articlesId;

    @NotNull
    @Size(min = 10, max = 100)
    private String title;

    @NotNull
    @Size(min = 3, max = 50)
    private String category;

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
    private String mdDesc;

    @NotNull
    @Size(min = 1, max = 10)
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
