package com.backend.api.repository.postgres;

import com.backend.api.entity.TourLogEntity;
import com.backend.api.request.TourLogCreatePayload;
import com.backend.api.request.TourLogCreateReq;
import com.backend.api.request.TourLogPlacePayload;
import com.backend.api.request.TourLogUpdatePayload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TourLogRepository {
    void createTourLogs(@Param("toursId") int toursId, @Param("tourLogs") List<TourLogCreatePayload> tourLogCreatePayloads);

    int updateTourLogs(@Param("toursId") int toursId, @Param("tourLogs") List<TourLogUpdatePayload> tourLogUpdatePayloads);

    int deleteTourLogPlaces(@Param("tourLogIds") List<Integer> tourLogIds);

    void createDraftTourLog(TourLogCreateReq tourLogCreateReq);

    TourLogEntity getTourLogByTourIdAndDate(int toursId, String date);

    void createTourLogsPlaces(@Param("tourLogsPlaces") List<TourLogPlacePayload> tourLogPlacePayloads);

    int deleteTourLogsById(int id);

    List<Integer> getTourLogIdsByTourId(int toursId);
}
