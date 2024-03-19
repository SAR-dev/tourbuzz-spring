package com.backend.api.repository.postgres;

import com.backend.api.entity.TourTypeEntity;
import com.backend.api.request.admin.TourTypeCreateReq;
import com.backend.api.request.admin.TourTypeUpdateReq;
import com.backend.api.response.TourTypeCheckRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TourTypeRepository {
    TourTypeCheckRes checkTourType(TourTypeCreateReq tourTypeCreateReq);

    void createTourType(TourTypeCreateReq tourTypeCreateReq);

    int updateTourType(TourTypeUpdateReq tourTypeUpdateReq);

    List<TourTypeEntity> getTourTypes(String name);

    TourTypeEntity getTourTypeById(int id);

    void deleteTourTypeById(int id);
}
