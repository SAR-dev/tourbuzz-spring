package com.backend.api.repository.postgres;

import com.backend.api.entity.HotelEntity;
import com.backend.api.request.HotelCreateReq;
import com.backend.api.request.HotelUpdateReq;
import com.backend.api.response.HotelCheckRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelRepository {
    List<HotelEntity> getHotels();

    HotelCheckRes checkHotel(HotelCreateReq hotelCreateReq);

    void createHotel(HotelCreateReq hotelCreateReq);

    int updateHotel(HotelUpdateReq hotelUpdateReq);

    int deleteHotelById(int id);
}
