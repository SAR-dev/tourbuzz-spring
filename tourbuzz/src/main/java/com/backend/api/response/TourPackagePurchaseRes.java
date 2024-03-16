package com.backend.api.response;

import com.backend.api.entity.PackageEntity;
import com.backend.api.entity.TourEntity;
import lombok.Data;

@Data
public class TourPackagePurchaseRes {
    private PackageEntity tour_package;
    private TourEntity tour;
}
