package com.backend.api.response;

import com.backend.api.entity.PackageEntity;
import com.backend.api.entity.TourEntity;
import com.backend.api.entity.TourShortEntity;
import lombok.Data;

@Data
public class TourPackageBookedRes {
    private PackageEntity tour_package;
    private TourShortEntity tour;
    private int ticket_sold;
}
