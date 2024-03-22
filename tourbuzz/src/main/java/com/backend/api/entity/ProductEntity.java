package com.backend.api.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class ProductEntity {
    private int id;
    private int categories_id;
    private String name;
    private String slug;
    private String seo;
    private String video_link;
    private String image_ids;
    private String description;
    private long actual_price;
    private long offer_price;
    private boolean active;
    private int threshold;
    private String tags;
    private Timestamp created_at;
    private Timestamp updated_at;

    private CategoryEntity category;
    private List<ImageEntity> images;
}
