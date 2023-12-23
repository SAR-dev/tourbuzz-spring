package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class ArticleEntity {
    private int id;
    private int writers_id;
    private String slug;
    private String title;
    private String subtitle;
    private String summary;
    private String md_desc;
    private String image_ids;
    private Timestamp created_at;
    private Timestamp updated_at;

    private WriterEntity writer;
    private List<ImageEntity> images;
}
