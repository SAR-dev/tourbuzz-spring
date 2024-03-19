package com.backend.api.repository.postgres;

import com.backend.api.entity.TourEntity;
import com.backend.api.request.admin.*;
import com.backend.api.response.TourCheckRes;
import com.backend.api.response.TourListRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TourRepository {
    TourCheckRes checkTour(TourCreatePayload tourCreatePayload);

    int createTour(TourCreatePayload tourCreatePayload);

    int updateTour(TourUpdatePayload tourUpdatePayload);

    List<TourListRes> getTours();

    List<TourListRes> getUpcomingTours();

    List<TourListRes> getToursByPlaceId(int placesId);

    List<TourListRes> getToursByHotelId(int hotelsId);

    List<TourListRes> getToursByOrganizerId(int organizersId);

    TourEntity getTourById(int id);

    TourEntity getTourBySlug(String slug);

    void deleteTourById(int id);
}
