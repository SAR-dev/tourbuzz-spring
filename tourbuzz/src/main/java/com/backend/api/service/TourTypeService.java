package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.TourTypeEntity;
import com.backend.api.repository.postgres.TourTypeRepository;
import com.backend.api.request.admin.TourTypeCreateReq;
import com.backend.api.request.admin.TourTypeUpdateReq;
import com.backend.api.response.TourTypeCheckRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourTypeService {
    private final TourTypeRepository tourTypeRepository;

    public TourTypeCheckRes checkTourType(TourTypeCreateReq tourTypeCreateReq){
        return tourTypeRepository.checkTourType(tourTypeCreateReq);
    }

    @Transactional
    public void createTourType(TourTypeCreateReq tourTypeCreateReq) throws Exception {
        TourTypeCheckRes tourTypeCheckRes = checkTourType(tourTypeCreateReq);
        List<String> messages = new ArrayList<>();
        if(tourTypeCheckRes.isSlug_exists()){
            messages.add(ErrorMessages.SLUG_EXISTS);
        }
        if(tourTypeCheckRes.isName_exists()){
            messages.add(ErrorMessages.NAME_EXISTS);
        }
        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
        tourTypeRepository.createTourType(tourTypeCreateReq);
    }

    public List<TourTypeEntity> getTourTypes(String name){
        return tourTypeRepository.getTourTypes(name);
    }

    public TourTypeEntity getTourTypeById(int id){
        return tourTypeRepository.getTourTypeById(id);
    }

    public void deleteTourTypeById(int id) {
        tourTypeRepository.deleteTourTypeById(id);
    }

    public void updateTourType(TourTypeUpdateReq tourTypeUpdateReq) throws Exception {
        int count = tourTypeRepository.updateTourType(tourTypeUpdateReq);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }
}
