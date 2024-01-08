package com.backend.api.service;

import com.backend.api.repository.postgres.TourRepository;
import com.backend.api.request.TourCreatePayload;
import com.backend.api.request.TourCreateRequest;
import com.backend.api.request.TourLogCreatePayload;
import com.backend.api.response.TourCheckRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;

    public TourCheckRes checkTour(TourCreatePayload tourCreatePayload){
        return tourRepository.checkTour(tourCreatePayload);
    }

    @Transactional
    public void createTour(TourCreateRequest tourCreateRequest) throws ValidationException {
        TourCheckRes tourCheckRes = checkTour(tourCreateRequest.getTour());
        List<String> messages = new ArrayList<>();
        if(tourCheckRes.isSlug_exists()){
            messages.add("Slug already exists.");
        }
        if(tourCheckRes.isTitle_exists()){
            messages.add("Title already exists.");
        }
        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
        int toursId = tourRepository.createTour(tourCreateRequest.getTour());
        createTourLogs(toursId, tourCreateRequest.getTourLogs());
    }

    public void createTourLogs(int toursId, List<TourLogCreatePayload> tourLogCreatePayloads){
        tourRepository.createTourLogs(toursId, tourLogCreatePayloads);
    }
}
