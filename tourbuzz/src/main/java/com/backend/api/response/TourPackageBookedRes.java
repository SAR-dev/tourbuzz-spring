package com.backend.api.response;

import com.backend.api.entity.PackageEntity;
import com.backend.api.entity.TourShortEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TourPackageBookedRes {
    private PackageEntity tourPackage;
    private TourShortEntity tour;
    private int ticketSold;
}
