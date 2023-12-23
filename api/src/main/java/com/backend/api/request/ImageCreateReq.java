package com.backend.api.request;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ImageCreateReq {
    private String title;
    private String sourceUrl;
    private String thumbnailUrl;
    private String optimizedUrl;
}
