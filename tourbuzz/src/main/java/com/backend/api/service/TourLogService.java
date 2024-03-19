package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.TourLogEntity;
import com.backend.api.repository.postgres.TourLogRepository;
import com.backend.api.request.admin.TourLogCreatePayload;
import com.backend.api.request.admin.TourLogCreateReq;
import com.backend.api.request.admin.TourLogPlacePayload;
import com.backend.api.request.admin.TourLogUpdatePayload;
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
    public void createDraftTourLog(TourLogCreateReq tourLogCreateReq) throws Exception {
        TourLogEntity tourLogEntity = getTourLogByTourIdAndDate(tourLogCreateReq.getToursId(), tourLogCreateReq.getDate());
        if(!Objects.isNull(tourLogEntity)){
            throw new Exception(ErrorMessages.NON_UNIQUE_TOUR_LOG_DATE);
        }
        tourLogRepository.createDraftTourLog(tourLogCreateReq);

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

    public void deleteTourLogsById(int id) {
        tourLogRepository.deleteTourLogsById(id);
    }

    public List<Integer> getTourLogIdsByTourId(int toursId){
        return tourLogRepository.getTourLogIdsByTourId(toursId);
    }

    @Transactional
    public void updateTourLogs(int toursId, List<TourLogUpdatePayload> tourLogUpdatePayloads) throws Exception {
        int lCount = tourLogRepository.updateTourLogs(toursId, tourLogUpdatePayloads);
        if(lCount < 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
        List<Integer> tourLogIds = tourLogUpdatePayloads.stream().map(TourLogUpdatePayload::getId).toList();
        tourLogRepository.deleteTourLogPlaces(tourLogIds);
        List<TourLogPlacePayload> tourLogPlacePayloads = new ArrayList<>();
        for(TourLogUpdatePayload tourLogUpdatePayload : tourLogUpdatePayloads) {
            tourLogUpdatePayload.getPlaceIds().forEach(placeId -> {
                tourLogPlacePayloads.add(TourLogPlacePayload
                        .builder()
                        .toursId(toursId)
                        .tourLogDate(tourLogUpdatePayload.getDate())
                        .placesId(placeId)
                        .build()
                );
            });
        }
        createTourLogsPlaces(tourLogPlacePayloads);
    }
}
