package com.backend.api.service;

import com.backend.api.entity.DistrictEntity;
import com.backend.api.entity.PlaceEntity;
import com.backend.api.repository.postgres.PlaceRepository;
import com.backend.api.response.DivisionWithDistrictsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    public List<PlaceEntity> getPlaces(){
        return placeRepository.getPlaces();
    }

    public List<DivisionWithDistrictsRes> getDivisionsWithDistricts(){
        return placeRepository.getDivisionsWithDistricts();
    }

    public List<DistrictEntity> getDistricts(){
        return placeRepository.getDistricts();
    }
}
