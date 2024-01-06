package com.backend.api.service;

import com.backend.api.entity.HotelEntity;
import com.backend.api.repository.postgres.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public List<HotelEntity> getHotels(){
        return hotelRepository.getHotels();
    }
}
