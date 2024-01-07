package com.backend.api.request;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class TourCreateRequest {
    private int tour_types_id;
    private int organizers_id;
    private int images_id;
    private String title;
    private String slug;
    private String md_desc;
    private String summary;
    private String group_size;
    private String group_info;
    private String services;
    private String transports;
    private String cancel_ins;
    private String start_ins;
    private String finish_ins;
    private Timestamp start_at;
    private Timestamp finish_at;
    private Timestamp last_sell_at;
    private String image_ids;
}
