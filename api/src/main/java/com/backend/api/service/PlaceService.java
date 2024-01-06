package com.backend.api.service;

import com.backend.api.entity.PlaceEntity;
import com.backend.api.repository.postgres.PlaceRepository;
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
}
