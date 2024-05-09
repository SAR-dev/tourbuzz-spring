package com.backend.api.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TourEntity {
    private int id;
    private int tourTypesId;
    private int organizersId;
    private int imagesId;
    private String title;
    private String videoLink;
    private String slug;
    private String mdDesc;
    private String highlights;
    private String faq;
    private String groupSize;
    private String groupInfo;
    private String transports;
    private String cancelIns;
    private String startIns;
    private String finishIns;
    private Timestamp startAt;
    private Timestamp finishAt;
    private Timestamp lastSellAt;
    private String imageIds;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private TourTypeEntity typeEntity;
    private OrganizerEntity organizer;
    private ImageEntity avatar;
    private List<ImageEntity> images;
    private List<PackageEntity> packages;
    private List<TourLogEntity> tourLogs;
    private List<PlaceEntity> places;

    private boolean expired;

    public boolean isExpired() {
        return getLastSellAt().before(new Date());
    }
}
