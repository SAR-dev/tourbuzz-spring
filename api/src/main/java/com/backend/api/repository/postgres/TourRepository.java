package com.backend.api.repository.postgres;

import com.backend.api.request.TourCreatePayload;
import com.backend.api.request.TourLogCreatePayload;
import com.backend.api.response.TourCheckRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TourRepository {
    TourCheckRes checkTour(TourCreatePayload tourCreatePayload);

    int createTour(TourCreatePayload tourCreatePayload);

    void createTourLogs(@Param("toursId") int toursId, @Param("tourLogs") List<TourLogCreatePayload> tourLogCreatePayloads);
}
