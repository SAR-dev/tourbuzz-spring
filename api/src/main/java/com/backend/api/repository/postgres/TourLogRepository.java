package com.backend.api.repository.postgres;

import com.backend.api.request.TourLogCreatePayload;
import com.backend.api.request.TourLogPlacePayload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TourLogRepository {
    void createTourLogs(@Param("toursId") int toursId, @Param("tourLogs") List<TourLogCreatePayload> tourLogCreatePayloads);

    void createTourLogsPlaces(@Param("tourLogsPlaces") List<TourLogPlacePayload> tourLogPlacePayloads);
}
