package com.backend.api.entity;

import com.backend.api.enums.Status;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TourLogEntity {
    private int id;
    private int toursId;
    private int hotelsId;
    private String title;
    private String description;
    private LocalDate date;
    private String imageIds;
    private Status status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    private List<ImageEntity> images;
    private HotelEntity hotel;
    private List<PlaceEntity> places;
}
