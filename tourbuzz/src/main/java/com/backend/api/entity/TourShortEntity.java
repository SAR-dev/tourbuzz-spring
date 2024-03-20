package com.backend.api.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class TourShortEntity {
    private int id;
    private String title;
    private String slug;
    private String group_size;
    private String group_info;
    private String transports;
    private Timestamp start_at;
    private Timestamp finish_at;
    private Timestamp last_sell_at;
    private Timestamp created_at;
    private Timestamp updated_at;

    private OrganizerEntity organizer;
    private ImageEntity avatar;

    private boolean expired;

    public boolean isExpired() {
        return getLast_sell_at().before(new Date());
    }
}
