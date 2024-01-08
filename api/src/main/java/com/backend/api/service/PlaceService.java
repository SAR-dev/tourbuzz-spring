package com.backend.api.service;

import com.backend.api.entity.DistrictEntity;
import com.backend.api.entity.PlaceEntity;
import com.backend.api.repository.postgres.PlaceRepository;
import com.backend.api.request.HotelCreateReq;
import com.backend.api.request.HotelUpdateReq;
import com.backend.api.request.PlaceCreateReq;
import com.backend.api.request.PlaceUpdateReq;
import com.backend.api.response.DivisionWithDistrictsRes;
import com.backend.api.response.HotelCheckRes;
import com.backend.api.response.PlaceCheckRes;
import com.backend.api.response.PlaceTypeRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    @Transactional
    public void createPlace(PlaceCreateReq placeCreateReq) throws Exception {
        PlaceCheckRes placeCheckRes = checkPlace(placeCreateReq);
        List<String> messages = new ArrayList<>();
        if(placeCheckRes.isName_exists()){
            messages.add("Name already exists.");
        }
        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
        placeRepository.createPlace(placeCreateReq);
    }

    public void deletePlaceById(int id) throws Exception {
        int count = placeRepository.deletePlaceById(id);
        if(count != 1){
            throw new Exception("Place could not be deleted");
        }
    }

    public void updatePlace(PlaceUpdateReq placeUpdateReq) throws Exception {
        int count = placeRepository.updatePlace(placeUpdateReq);
        if(count != 1){
            throw new Exception("Place could not be updated");
        }
    }

    public List<PlaceEntity> getPlaces(String name){
        return placeRepository.getPlaces(name);
    }

    public PlaceEntity getPlaceById(int id){
        return placeRepository.getPlaceById(id);
    }

    public PlaceCheckRes checkPlace(PlaceCreateReq placeCreateReq){
        return placeRepository.checkPlace(placeCreateReq);
    }

    public List<PlaceTypeRes> getPlaceTypes(){
        return placeRepository.getPlaceTypes();
    }

}
