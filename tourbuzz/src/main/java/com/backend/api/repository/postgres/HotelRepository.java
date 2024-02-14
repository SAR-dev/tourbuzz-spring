package com.backend.api.repository.postgres;

import com.backend.api.entity.HotelEntity;
import com.backend.api.request.admin.HotelCreateReq;
import com.backend.api.request.admin.HotelUpdateReq;
import com.backend.api.response.HotelCheckRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelRepository {
    List<HotelEntity> getHotels(String name);

    List<HotelEntity> getRandomHotels(int count);

    HotelEntity getHotelById(int id);

    HotelCheckRes checkHotel(HotelCreateReq hotelCreateReq);

    void createHotel(HotelCreateReq hotelCreateReq);

    int updateHotel(HotelUpdateReq hotelUpdateReq);

    int deleteHotelById(int id);
}
