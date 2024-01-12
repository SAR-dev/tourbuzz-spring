package com.backend.api.service;

import com.backend.api.Constants;
import com.backend.api.entity.TourEntity;
import com.backend.api.repository.postgres.TourRepository;
import com.backend.api.request.*;
import com.backend.api.response.TourCheckRes;
import com.backend.api.response.TourListRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final ConverterService converterService;
    private final ValidationService validationService;
    private final TourLogService tourLogService;
    private final PackageService packageService;

    public TourCheckRes checkTour(TourCreatePayload tourCreatePayload){
        return tourRepository.checkTour(tourCreatePayload);
    }

    @Transactional
    public void createTour(TourCreateRequest tourCreateRequest) throws Exception {
        verifyCreateTour(tourCreateRequest);
        int toursId = tourRepository.createTour(tourCreateRequest.getTour());
        tourLogService.createTourLogs(toursId, tourCreateRequest.getTourLogs());
        packageService.createPackages(toursId, tourCreateRequest.getPackages());
    }

    public void verifyCreateTour(TourCreateRequest tourCreateRequest) throws Exception {
        TourCheckRes tourCheckRes = checkTour(tourCreateRequest.getTour());
        List<String> messages = new ArrayList<>();
        if(tourCheckRes.isSlug_exists()){
            messages.add(Constants.SLUG_EXISTS);
        }
        if(tourCheckRes.isTitle_exists()){
            messages.add(Constants.TITLE_EXISTS);
        }

        LocalDate tourStartsAt = converterService.parseStringToDate(tourCreateRequest.getTour().getStartAt(), Constants.DD_MM_YYYY_HH_MM_FORMAT);
        LocalDate tourFinishAt = converterService.parseStringToDate(tourCreateRequest.getTour().getFinishAt(), Constants.DD_MM_YYYY_HH_MM_FORMAT);
        LocalDate tourLastSellAt = converterService.parseStringToDate(tourCreateRequest.getTour().getLastSellAt(), Constants.DD_MM_YYYY_HH_MM_FORMAT);

        LocalDate dateAfterTwoDays = LocalDate.now().plusDays(Constants.TOUR_DATE_AFTER_MIN);
        if(!tourStartsAt.isAfter(dateAfterTwoDays)){
            messages.add(Constants.TOUR_DATE_LESS_THAN_MIN_DATE);
        }
        if(!tourFinishAt.isAfter(tourStartsAt)){
            messages.add(Constants.TOUR_FINISH_SHOULD_BE_AFTER_START);
        }
        if(!tourLastSellAt.isBefore(tourStartsAt)){
            messages.add(Constants.TOUR_LAST_SELL_SHOULD_BE_BEFORE_START);
        }
        List<LocalDate> tourLogsDates = new ArrayList<>();
        for(String dateString : tourCreateRequest.getTourLogs().stream().map(TourLogCreatePayload::getDate).toList()) {
            tourLogsDates.add(converterService.parseStringToDate(dateString, Constants.DD_MM_YYYY_FORMAT));
        }
        if(!validationService.areDatesUnique(tourLogsDates)){
            messages.add(Constants.NON_UNIQUE_TOUR_LOG_DATE);
        }
        if(!validationService.areDatesInRange(tourLogsDates, tourStartsAt, tourFinishAt)){
            messages.add(Constants.TOUR_LOG_SHOULD_BE_BETWEEN_START_END);
        }

        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
    }

    public List<TourListRes> getTours(){
        return tourRepository.getTours();
    }

    public TourEntity getTourById(int id){
        return tourRepository.getTourById(id);
    }
}
