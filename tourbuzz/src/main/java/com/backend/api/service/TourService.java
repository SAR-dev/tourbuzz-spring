package com.backend.api.service;

import com.backend.api.Constants;
import com.backend.api.ErrorMessages;
import com.backend.api.entity.TourEntity;
import com.backend.api.repository.postgres.TourRepository;
import com.backend.api.request.admin.*;
import com.backend.api.response.TourCheckRes;
import com.backend.api.response.TourListRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TourService {
    private final TourRepository tourRepository;
    private final UtilsService utilsService;
    private final ValidationService validationService;
    private final TourLogService tourLogService;
    private final PackageService packageService;

    public TourCheckRes checkTour(TourCreatePayload tourCreatePayload){
        return tourRepository.checkTour(tourCreatePayload);
    }

    @Transactional
    public void createTour(TourCreateReq tourCreateReq) throws Exception {
        verifyCreateTour(tourCreateReq);
        int toursId = tourRepository.createTour(tourCreateReq.getTour());
        tourLogService.createTourLogs(toursId, tourCreateReq.getTourLogs());
        packageService.createPackages(toursId, tourCreateReq.getPackages());
    }

    @Transactional
    public void updateTour(TourUpdateReq tourUpdateReq) throws Exception {
        verifyUpdateTour(tourUpdateReq);
        updateTour(tourUpdateReq.getTour());
        tourLogService.updateTourLogs(tourUpdateReq.getTour().getId(), tourUpdateReq.getTourLogs());
        packageService.updatePackages(tourUpdateReq.getTour().getId(), tourUpdateReq.getPackages());
    }

    @Transactional
    public void verifyCreateTour(TourCreateReq tourCreateReq) throws Exception {
        TourCheckRes tourCheckRes = checkTour(tourCreateReq.getTour());
        List<String> messages = new ArrayList<>();
        if(tourCheckRes.isSlugExists()){
            messages.add(ErrorMessages.SLUG_EXISTS);
        }
        if(tourCheckRes.isTitleExists()){
            messages.add(ErrorMessages.TITLE_EXISTS);
        }

        LocalDate tourStartsAt = utilsService.parseStringToDate(tourCreateReq.getTour().getStartAt(), Constants.DATES.DD_MM_YYYY_HH_MM_FORMAT);
        LocalDate tourFinishAt = utilsService.parseStringToDate(tourCreateReq.getTour().getFinishAt(), Constants.DATES.DD_MM_YYYY_HH_MM_FORMAT);
        LocalDate tourLastSellAt = utilsService.parseStringToDate(tourCreateReq.getTour().getLastSellAt(), Constants.DATES.DD_MM_YYYY_HH_MM_FORMAT);

        LocalDate dateAfterTwoDays = LocalDate.now().plusDays(Constants.TOUR_DATE_AFTER_MIN);
        if(!tourStartsAt.isAfter(dateAfterTwoDays)){
            messages.add(ErrorMessages.TOUR_DATE_LESS_THAN_MIN_DATE);
        }
        if(!tourFinishAt.isAfter(tourStartsAt)){
            messages.add(ErrorMessages.TOUR_FINISH_SHOULD_BE_AFTER_START);
        }
        if(!tourLastSellAt.isBefore(tourStartsAt)){
            messages.add(ErrorMessages.TOUR_LAST_SELL_SHOULD_BE_BEFORE_START);
        }
        List<LocalDate> tourLogsDates = new ArrayList<>();
        for(String dateString : tourCreateReq.getTourLogs().stream().map(TourLogCreatePayload::getDate).toList()) {
            tourLogsDates.add(utilsService.parseStringToDate(dateString, Constants.DATES.DD_MM_YYYY_FORMAT));
        }
        if(validationService.areDatesNotUnique(tourLogsDates)){
            messages.add(ErrorMessages.NON_UNIQUE_TOUR_LOG_DATE);
        }
        if(validationService.areDatesNotInRange(tourLogsDates, tourStartsAt, tourFinishAt)){
            messages.add(ErrorMessages.TOUR_LOG_SHOULD_BE_BETWEEN_START_END);
        }

        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
    }

    @Transactional
    public void verifyUpdateTour(TourUpdateReq tourUpdateReq) throws Exception {
        List<Integer> tourLogIds = tourLogService.getTourLogIdsByTourId(tourUpdateReq.getTour().getId());
        List<Integer> reqTourLogIds = tourUpdateReq.getTourLogs().stream().map(TourLogUpdatePayload::getId).toList();
        List<Integer> packageIds = packageService.getPackageIdsByTourId(tourUpdateReq.getTour().getId());
        List<Integer> reqPackageIds = tourUpdateReq.getPackages().stream().map(TourPackageUpdatePayload::getId).toList();

        boolean allTourLogIdsExist = new HashSet<>(tourLogIds).containsAll(reqTourLogIds);
        boolean allPackageIdsExist = new HashSet<>(packageIds).containsAll(reqPackageIds);
        if(!allPackageIdsExist || !allTourLogIdsExist){
            throw new Exception(ErrorMessages.MISSING_DATA);
        }

        List<String> messages = new ArrayList<>();

        LocalDate tourStartsAt = utilsService.parseStringToDate(tourUpdateReq.getTour().getStartAt(), Constants.DATES.DD_MM_YYYY_HH_MM_FORMAT);
        LocalDate tourFinishAt = utilsService.parseStringToDate(tourUpdateReq.getTour().getFinishAt(), Constants.DATES.DD_MM_YYYY_HH_MM_FORMAT);
        LocalDate tourLastSellAt = utilsService.parseStringToDate(tourUpdateReq.getTour().getLastSellAt(), Constants.DATES.DD_MM_YYYY_HH_MM_FORMAT);

        if(!tourFinishAt.isAfter(tourStartsAt)){
            messages.add(ErrorMessages.TOUR_FINISH_SHOULD_BE_AFTER_START);
        }
        if(!tourLastSellAt.isBefore(tourStartsAt)){
            messages.add(ErrorMessages.TOUR_LAST_SELL_SHOULD_BE_BEFORE_START);
        }
        List<LocalDate> tourLogsDates = new ArrayList<>();
        for(String dateString : tourUpdateReq.getTourLogs().stream().map(TourLogUpdatePayload::getDate).toList()) {
            tourLogsDates.add(utilsService.parseStringToDate(dateString, Constants.DATES.DD_MM_YYYY_FORMAT));
        }
        if(validationService.areDatesNotUnique(tourLogsDates)){
            messages.add(ErrorMessages.NON_UNIQUE_TOUR_LOG_DATE);
        }
        if(validationService.areDatesNotInRange(tourLogsDates, tourStartsAt, tourFinishAt)){
            messages.add(ErrorMessages.TOUR_LOG_SHOULD_BE_BETWEEN_START_END);
        }

        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
    }

    public List<TourListRes> getTours(){
        return tourRepository.getTours();
    }

    public List<TourListRes> getUpcomingTours(){
        return tourRepository.getUpcomingTours();
    }

    public List<TourListRes> getToursByPlaceId(int placesId){
        return tourRepository.getToursByPlaceId(placesId);
    }

    public List<TourListRes> getToursByHotelId(int hotelsId){
        return tourRepository.getToursByHotelId(hotelsId);
    }

    public List<TourListRes> getToursByOrganizerId(int organizersId){
        return tourRepository.getToursByOrganizerId(organizersId);
    }

    public TourEntity getTourById(int id){
        return tourRepository.getTourById(id);
    }

    public TourEntity getTourBySlug(String slug) throws NotFoundException {
        TourEntity tour = tourRepository.getTourBySlug(slug);
        if (Objects.isNull(tour)) {
            throw new NotFoundException(ErrorMessages.NOT_FOUND);
        }
        return tour;
    }

    @Transactional
    public void deleteTourById(int id) throws Exception {
        tourRepository.deleteTourById(id);
    }

    @Transactional
    public void updateTour(TourUpdatePayload tourUpdatePayload) throws Exception {
        int count = tourRepository.updateTour(tourUpdatePayload);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }
}
