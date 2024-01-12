package com.backend.api.service;

import com.backend.api.Constants;
import com.backend.api.repository.postgres.TourLogRepository;
import com.backend.api.request.TourLogCreatePayload;
import com.backend.api.request.TourLogCreateRequest;
import com.backend.api.request.TourLogPlacePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourLogService {
    private final TourLogRepository tourLogRepository;

    @Transactional
    public void createTourLogs(int toursId, List<TourLogCreatePayload> tourLogCreatePayloads){
        tourLogRepository.createTourLogs(toursId, tourLogCreatePayloads);
        createTourLogsPlacesFromList(toursId, tourLogCreatePayloads);
    }

    public void createTourLog(TourLogCreateRequest tourLogCreateRequest){
        tourLogRepository.createTourLog(tourLogCreateRequest);

        List<TourLogPlacePayload> tourLogPlacePayloads = new ArrayList<>();
        tourLogCreateRequest.getPlaceIds().forEach(placeId -> {
            tourLogPlacePayloads.add(TourLogPlacePayload
                    .builder()
                    .toursId(tourLogCreateRequest.getToursId())
                    .tourLogDate(tourLogCreateRequest.getDate())
                    .placesId(placeId)
                    .build()
            );
        });
        createTourLogsPlaces(tourLogPlacePayloads);

    }

    public void createTourLogsPlacesFromList(int toursId, List<TourLogCreatePayload> tourLogCreatePayloads){
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

    public void createTourLogsPlaces(List<TourLogPlacePayload> tourLogPlacePayloads){
        tourLogRepository.createTourLogsPlaces(tourLogPlacePayloads);
    }

    public void deleteTourLogsById(int id) throws Exception {
        int count = tourLogRepository.deleteTourLogsById(id);
        if(count != 1){
            throw new Exception(Constants.DELETE_FAILED);
        }
    }
}
