package com.backend.api.repository.postgres;

import com.backend.api.entity.PlaceEntity;
import com.backend.api.response.DivisionWithDistrictsRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceRepository {
    List<PlaceEntity> getPlaces();

    List<DivisionWithDistrictsRes> getDivisionsWithDistricts();
}
