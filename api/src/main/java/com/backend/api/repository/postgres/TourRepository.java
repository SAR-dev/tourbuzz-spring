package com.backend.api.repository.postgres;

import com.backend.api.request.TourCreatePayload;
import com.backend.api.request.TourLogCreatePayload;
import com.backend.api.request.TourLogPlacePayload;
import com.backend.api.request.TourPackageCreatePayload;
import com.backend.api.response.TourCheckRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TourRepository {
    TourCheckRes checkTour(TourCreatePayload tourCreatePayload);

    int createTour(TourCreatePayload tourCreatePayload);

    void createTourLogs(@Param("toursId") int toursId, @Param("tourLogs") List<TourLogCreatePayload> tourLogCreatePayloads);

    void createTourPackages(@Param("toursId") int toursId, @Param("tourPackages")List<TourPackageCreatePayload> tourPackageCreatePayloads);

    void createTourLogsPlaces(@Param("tourLogsPlaces") List<TourLogPlacePayload> tourLogPlacePayloads);
}
