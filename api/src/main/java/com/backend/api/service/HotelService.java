package com.backend.api.service;

import com.backend.api.entity.HotelEntity;
import com.backend.api.repository.postgres.HotelRepository;
import com.backend.api.request.HotelCreateReq;
import com.backend.api.request.HotelUpdateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.request.WriterUpdateReq;
import com.backend.api.response.HotelCheckRes;
import com.backend.api.response.WriterCheckRes;
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

    public HotelEntity getHotelById(int id){
        return hotelRepository.getHotelById(id);
    }

    @Transactional
    public void createHotel(HotelCreateReq hotelCreateReq) throws Exception {
        HotelCheckRes hotelCheckRes = checkHotel(hotelCreateReq);
        List<String> messages = new ArrayList<>();
        if(hotelCheckRes.isName_exists()){
            messages.add("Name already exists.");
        }
        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
        hotelRepository.createHotel(hotelCreateReq);
    }

    public void deleteHotelById(int id) throws Exception {
        int count = hotelRepository.deleteHotelById(id);
        if(count != 1){
            throw new Exception("Hotel could not be deleted");
        }
    }

    public void updateHotel(HotelUpdateReq hotelUpdateReq) throws Exception {
        int count = hotelRepository.updateHotel(hotelUpdateReq);
        if(count != 1){
            throw new Exception("Hotel could not be updated");
        }
    }

    public HotelCheckRes checkHotel(HotelCreateReq hotelCreateReq){
        return hotelRepository.checkHotel(hotelCreateReq);
    }
}
