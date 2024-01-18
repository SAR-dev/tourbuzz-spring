package com.backend.api.request.admin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourLogPlacePayload {
    private int toursId;
    private String tourLogDate;
    private int placesId;
}
