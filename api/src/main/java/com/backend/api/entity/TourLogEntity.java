package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
public class TourLogEntity {
    private int id;
    private int tours_id;
    private int hotels_id;
    private String title;
    private String description;
    private LocalDate date;
    private String image_ids;
    private Timestamp created_at;
    private Timestamp updated_at;

    private List<ImageEntity> images;
    private HotelEntity hotel;
    private List<PlaceEntity> places;
}
