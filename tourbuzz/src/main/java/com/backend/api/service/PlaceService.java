package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.PlaceEntity;
import com.backend.api.repository.postgres.PlaceRepository;
import com.backend.api.request.admin.PlaceCreateReq;
import com.backend.api.request.admin.PlaceUpdateReq;
import com.backend.api.response.PlaceCheckRes;
import com.backend.api.response.PlaceTypeRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    @Transactional
    public void createPlace(PlaceCreateReq placeCreateReq) throws Exception {
        PlaceCheckRes placeCheckRes = checkPlace(placeCreateReq);
        List<String> messages = new ArrayList<>();
        if(placeCheckRes.isNameExists()){
            messages.add(ErrorMessages.NAME_EXISTS);
        }
        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
        placeRepository.createPlace(placeCreateReq);
    }

    public void deletePlaceById(int id) {
        placeRepository.deletePlaceById(id);
    }

    public void updatePlace(PlaceUpdateReq placeUpdateReq) throws Exception {
        int count = placeRepository.updatePlace(placeUpdateReq);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public List<PlaceEntity> getPlaces(String name){
        return placeRepository.getPlaces(name);
    }

    public List<PlaceEntity> getPopularPlaces(int count){
        return placeRepository.getPopularPlaces(count);
    }

    public PlaceEntity getPlaceById(int id) throws NotFoundException {
        PlaceEntity place = placeRepository.getPlaceById(id);
        if (Objects.isNull(place)) {
            throw new NotFoundException(ErrorMessages.NOT_FOUND);
        }
        return place;
    }

    public PlaceCheckRes checkPlace(PlaceCreateReq placeCreateReq){
        return placeRepository.checkPlace(placeCreateReq);
    }

    public List<PlaceTypeRes> getPlaceTypes(){
        return placeRepository.getPlaceTypes();
    }

}
