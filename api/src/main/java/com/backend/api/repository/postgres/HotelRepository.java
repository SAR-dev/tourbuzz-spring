package com.backend.api.repository.postgres;

import com.backend.api.entity.HotelEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotelRepository {
    List<HotelEntity> getHotels();
}
