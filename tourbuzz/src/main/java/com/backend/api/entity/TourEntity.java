package com.backend.api.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class TourEntity {
    private int id;
    private int tour_types_id;
    private int organizers_id;
    private int images_id;
    private String title;
    private String video_link;
    private String slug;
    private String md_desc;
    private String highlights;
    private String faq;
    private String group_size;
    private String group_info;
    private String transports;
    private String cancel_ins;
    private String start_ins;
    private String finish_ins;
    private Timestamp start_at;
    private Timestamp finish_at;
    private Timestamp last_sell_at;
    private String image_ids;
    private Timestamp created_at;
    private Timestamp updated_at;

    private TourTypeEntity tour_type;
    private OrganizerEntity organizer;
    private ImageEntity avatar;
    private List<ImageEntity> images;
    private List<PackageEntity> packages;
    private List<TourLogEntity> tour_logs;

    private boolean expired;

    public boolean isExpired() {
        return getLast_sell_at().before(new Date());
    }
}
