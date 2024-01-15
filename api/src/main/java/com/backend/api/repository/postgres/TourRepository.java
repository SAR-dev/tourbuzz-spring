package com.backend.api.repository.postgres;

import com.backend.api.entity.TourEntity;
import com.backend.api.request.TourCreatePayload;
import com.backend.api.request.TourLogCreatePayload;
import com.backend.api.request.TourLogPlacePayload;
import com.backend.api.request.TourPackageCreatePayload;
import com.backend.api.response.TourCheckRes;
import com.backend.api.response.TourListRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TourRepository {
    TourCheckRes checkTour(TourCreatePayload tourCreatePayload);

    int createTour(TourCreatePayload tourCreatePayload);

    List<TourListRes> getTours();

    TourEntity getTourById(int id);

    int deleteTourById(int id);
}
