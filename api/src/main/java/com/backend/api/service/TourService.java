package com.backend.api.service;

import com.backend.api.repository.postgres.TourRepository;
import com.backend.api.request.*;
import com.backend.api.response.TourCheckRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final ConverterService converterService;
    private final ValidationService validationService;

    public TourCheckRes checkTour(TourCreatePayload tourCreatePayload){
        return tourRepository.checkTour(tourCreatePayload);
    }

    @Transactional
    public void createTour(TourCreateRequest tourCreateRequest) throws Exception {
        verifyCreateTour(tourCreateRequest);
        int toursId = tourRepository.createTour(tourCreateRequest.getTour());
        createTourLogs(toursId, tourCreateRequest.getTourLogs());
        createTourPackages(toursId, tourCreateRequest.getPackages());
        createTourLogsPlaces(toursId, tourCreateRequest.getTourLogs());
    }

    public void verifyCreateTour(TourCreateRequest tourCreateRequest) throws Exception {
        TourCheckRes tourCheckRes = checkTour(tourCreateRequest.getTour());
        List<String> messages = new ArrayList<>();
        if(tourCheckRes.isSlug_exists()){
            messages.add("Slug already exists.");
        }
        if(tourCheckRes.isTitle_exists()){
            messages.add("Title already exists.");
        }

        LocalDate tourStartsAt = converterService.parseStringToDate(tourCreateRequest.getTour().getStartAt(), "yyyy/MM/dd/HH/mm");
        LocalDate tourFinishAt = converterService.parseStringToDate(tourCreateRequest.getTour().getFinishAt(), "yyyy/MM/dd/HH/mm");
        LocalDate tourLastSellAt = converterService.parseStringToDate(tourCreateRequest.getTour().getLastSellAt(), "yyyy/MM/dd/HH/mm");

        LocalDate dateAfterTwoDays = LocalDate.now().plusDays(2);
        if(!tourStartsAt.isAfter(dateAfterTwoDays)){
            messages.add("Tour start date must be after 3 days from current date");
        }
        if(!tourFinishAt.isAfter(tourStartsAt)){
            messages.add("Tour finish date must be after start date");
        }
        if(!tourLastSellAt.isBefore(tourStartsAt)){
            messages.add("Tour last sell date must be before start date");
        }
        List<LocalDate> tourLogsDates = new ArrayList<>();
        for(String dateString : tourCreateRequest.getTourLogs().stream().map(TourLogCreatePayload::getDate).toList()) {
            tourLogsDates.add(converterService.parseStringToDate(dateString, "yyyy/MM/dd"));
        }
        if(!validationService.areDatesUnique(tourLogsDates)){
            messages.add("Tour logs dates are not unique");
        }
        if(!validationService.areDatesInRange(tourLogsDates, tourStartsAt, tourFinishAt)){
            messages.add("Tour logs date should be between tour start and finish date");
        }

        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
    }

    public void createTourLogs(int toursId, List<TourLogCreatePayload> tourLogCreatePayloads){
        tourRepository.createTourLogs(toursId, tourLogCreatePayloads);
    }

    public void createTourPackages(int toursId, List<TourPackageCreatePayload> tourPackageCreatePayloads){
        tourRepository.createTourPackages(toursId, tourPackageCreatePayloads);
    }

    public void createTourLogsPlaces(int toursId, List<TourLogCreatePayload> tourLogCreatePayloads){
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
        tourRepository.createTourLogsPlaces(tourLogPlacePayloads);
    }
}
