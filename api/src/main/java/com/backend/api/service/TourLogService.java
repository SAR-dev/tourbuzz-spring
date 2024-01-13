package com.backend.api.service;

import com.backend.api.Constants;
import com.backend.api.entity.TourLogEntity;
import com.backend.api.repository.postgres.TourLogRepository;
import com.backend.api.request.TourLogCreatePayload;
import com.backend.api.request.TourLogCreateReq;
import com.backend.api.request.TourLogPlacePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TourLogService {
    private final TourLogRepository tourLogRepository;

    @Transactional
    public void createTourLogs(int toursId, List<TourLogCreatePayload> tourLogCreatePayloads){
        tourLogRepository.createTourLogs(toursId, tourLogCreatePayloads);
        List<TourLogPlacePayload> tourLogPlacePayloads = new ArrayList<>();
        for(TourLogCreatePayload tourLogCreatePayload : tourLogCreatePayloads) {
            tourLogCreatePayload.getPlaceIds().forEach(placeId -> {
                tourLogPlacePayloads.add(TourLogPlacePayload
                        .builder()
                        .toursId(toursId)
                        .tourLogDate(tourLogCreatePayload.getDate())
                        .placesId(placeId)
                        .build()
                );
            });
        }
        createTourLogsPlaces(tourLogPlacePayloads);
    }

    @Transactional
    public void createTourLog(TourLogCreateReq tourLogCreateReq) throws Exception {
        TourLogEntity tourLogEntity = getTourLogByTourIdAndDate(tourLogCreateReq.getToursId(), tourLogCreateReq.getDate());
        if(!Objects.isNull(tourLogEntity)){
            throw new Exception(Constants.NON_UNIQUE_TOUR_LOG_DATE);
        }
        tourLogRepository.createTourLog(tourLogCreateReq);

        List<TourLogPlacePayload> tourLogPlacePayloads = new ArrayList<>();
        tourLogCreateReq.getPlaceIds().forEach(placeId -> {
            tourLogPlacePayloads.add(TourLogPlacePayload
                    .builder()
                    .toursId(tourLogCreateReq.getToursId())
                    .tourLogDate(tourLogCreateReq.getDate())
                    .placesId(placeId)
                    .build()
            );
        });
        createTourLogsPlaces(tourLogPlacePayloads);
    }

    public void createTourLogsPlaces(List<TourLogPlacePayload> tourLogPlacePayloads){
        tourLogRepository.createTourLogsPlaces(tourLogPlacePayloads);
    }

    public TourLogEntity getTourLogByTourIdAndDate(int toursId, String date){
        return tourLogRepository.getTourLogByTourIdAndDate(toursId, date);
    }

    public void updateTourLog(){
//        get tour log dates
//        get previously included dates and new dates and create/update them
//        get dates that need to be deleted
    }

    public void deleteTourLogsById(int id) throws Exception {
        int count = tourLogRepository.deleteTourLogsById(id);
        if(count != 1){
            throw new Exception(Constants.DELETE_FAILED);
        }
    }
}
