package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.HotelEntity;
import com.backend.api.repository.postgres.HotelRepository;
import com.backend.api.request.admin.HotelCreateReq;
import com.backend.api.request.admin.HotelUpdateReq;
import com.backend.api.response.HotelCheckRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<HotelEntity> getHotels(String name){
        return hotelRepository.getHotels(name);
    }

    public List<HotelEntity> getRandomHotels(int count){
        return hotelRepository.getRandomHotels(count);
    }

    public HotelEntity getHotelById(int id){
        return hotelRepository.getHotelById(id);
    }

    @Transactional
    public void createHotel(HotelCreateReq hotelCreateReq) throws Exception {
        HotelCheckRes hotelCheckRes = checkHotel(hotelCreateReq);
        List<String> messages = new ArrayList<>();
        if(hotelCheckRes.isName_exists()){
            messages.add(ErrorMessages.NAME_EXISTS);
        }
        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
        hotelRepository.createHotel(hotelCreateReq);
    }

    public void deleteHotelById(int id) throws Exception {
        int count = hotelRepository.deleteHotelById(id);
        if(count != 1){
            throw new Exception(ErrorMessages.DELETE_FAILED);
        }
    }

    public void updateHotel(HotelUpdateReq hotelUpdateReq) throws Exception {
        int count = hotelRepository.updateHotel(hotelUpdateReq);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public HotelCheckRes checkHotel(HotelCreateReq hotelCreateReq){
        return hotelRepository.checkHotel(hotelCreateReq);
    }
}
