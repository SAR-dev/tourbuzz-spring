package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TourShortEntity {
    private int id;
    private String title;
    private String slug;
    private String groupSize;
    private String groupInfo;
    private String transports;
    private Timestamp startAt;
    private Timestamp finishAt;
    private Timestamp lastSellAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private OrganizerEntity organizer;
    private ImageEntity avatar;

    private boolean expired;

    public boolean isExpired() {
        return getLastSellAt().before(new Date());
    }
}
