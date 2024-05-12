package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductEntity {
    private int id;
    private int categoriesId;
    private int brandsId;
    private String title;
    private String slug;
    private String subtitle;
    private String seo;
    private String videoLink;
    private String imageIds;
    private String description;
    private long actualPrice;
    private long offerPrice;
    private boolean active;
    private int threshold;
    private String tags;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private CategoryEntity category;
    private List<ImageEntity> images;
    private BrandEntity brand;
}
