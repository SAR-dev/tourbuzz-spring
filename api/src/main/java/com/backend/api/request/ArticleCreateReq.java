package com.backend.api.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticleCreateReq {
    private String title;
    private String slug;
    private String subtitle;
    private String summary;
    private String md_desc;
    private List<Integer> image_ids;
    private int writers_id;
}
