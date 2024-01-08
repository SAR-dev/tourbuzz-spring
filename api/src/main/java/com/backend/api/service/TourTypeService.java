package com.backend.api.service;

import com.backend.api.entity.TourTypeEntity;
import com.backend.api.entity.WriterEntity;
import com.backend.api.repository.postgres.TourTypeRepository;
import com.backend.api.request.TourTypeCreateReq;
import com.backend.api.request.TourTypeUpdateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.request.WriterUpdateReq;
import com.backend.api.response.TourTypeCheckRes;
import com.backend.api.response.WriterCheckRes;
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
            messages.add("Slug already exists.");
        }
        if(tourTypeCheckRes.isName_exists()){
            messages.add("Name already exists.");
        }
        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
        tourTypeRepository.createTourType(tourTypeCreateReq);
    }

    public List<TourTypeEntity> getTourTypes(){
        return tourTypeRepository.getTourTypes();
    }

    public TourTypeEntity getTourTypeByeId(int id){
        return tourTypeRepository.getTourTypeByeId(id);
    }

    public void deleteTourTypeById(int id) throws Exception {
        int count = tourTypeRepository.deleteTourTypeById(id);
        if(count != 1){
            throw new Exception("Tour Type was not be deleted");
        }
    }

    public void updateTourType(TourTypeUpdateReq tourTypeUpdateReq) throws Exception {
        int count = tourTypeRepository.updateTourType(tourTypeUpdateReq);
        if(count != 1){
            throw new Exception("Tour Type could not be updated");
        }
    }
}
