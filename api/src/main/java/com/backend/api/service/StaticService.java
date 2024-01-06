package com.backend.api.service;

import com.backend.api.entity.DistrictEntity;
import com.backend.api.repository.postgres.StaticRepository;
import com.backend.api.response.DivisionWithDistrictsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaticService {
    private final StaticRepository staticRepository;

    public List<DivisionWithDistrictsRes> getDivisionsWithDistricts(){
        return staticRepository.getDivisionsWithDistricts();
    }

    public List<DistrictEntity> getDistricts(){
        return staticRepository.getDistricts();
    }
}
