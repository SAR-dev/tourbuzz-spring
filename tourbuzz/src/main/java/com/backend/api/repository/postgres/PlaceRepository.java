package com.backend.api.repository.postgres;

import com.backend.api.entity.DistrictEntity;
import com.backend.api.entity.PlaceEntity;
import com.backend.api.request.admin.PlaceCreateReq;
import com.backend.api.request.admin.PlaceUpdateReq;
import com.backend.api.response.DivisionWithDistrictsRes;
import com.backend.api.response.PlaceCheckRes;
import com.backend.api.response.PlaceTypeRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceRepository {
    PlaceCheckRes checkPlace(PlaceCreateReq placeCreateReq);

    void createPlace(PlaceCreateReq placeCreateReq);

    int updatePlace(PlaceUpdateReq placeUpdateReq);

    int deletePlaceById(int id);

    List<PlaceEntity> getPlaces(String name);

    List<PlaceEntity> getPopularPlaces(int count);

    PlaceEntity getPlaceById(int id);

    List<PlaceTypeRes> getPlaceTypes();
}
