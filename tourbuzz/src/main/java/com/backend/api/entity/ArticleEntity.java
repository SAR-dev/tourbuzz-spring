package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ArticleEntity {
    private int id;
    private int writersId;
    private String title;
    private String slug;
    private String subtitle;
    private String category;
    private String summary;
    private String mdDesc;
    private String imageIds;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private WriterEntity writer;
    private List<ImageEntity> images;
}
