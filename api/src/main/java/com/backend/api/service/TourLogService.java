package com.backend.api.service;

import com.backend.api.repository.postgres.TourLogRepository;
import com.backend.api.request.TourLogCreatePayload;
import com.backend.api.request.TourLogPlacePayload;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourLogService {
    private final TourLogRepository tourLogRepository;

    public void createTourLogs(int toursId, List<TourLogCreatePayload> tourLogCreatePayloads){
        tourLogRepository.createTourLogs(toursId, tourLogCreatePayloads);
    }

    public void createTourLogsPlaces(List<TourLogPlacePayload> tourLogPlacePayloads){
        tourLogRepository.createTourLogsPlaces(tourLogPlacePayloads);
    }
}
