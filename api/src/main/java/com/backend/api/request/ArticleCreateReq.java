package com.backend.api.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleCreateReq {
    private Article article;
    private Writer writer;

    @Data
    public class Article {
        private String title;
        private String slug;
        private String subtitle;
        private String summary;
        private String md_desc;
        private String image_ids;
    }

    @Data
    public class Writer {
        private String email;
        private String avatar;
        private String name;
    }


}
