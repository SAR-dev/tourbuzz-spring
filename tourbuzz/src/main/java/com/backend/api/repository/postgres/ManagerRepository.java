package com.backend.api.repository.postgres;

import com.backend.api.request.admin.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagerRepository {
    void createOrganizerManager(OrganizerManagerCreateReq organizerManagerCreateReq);

    void createHotelManager(HotelManagerCreateReq hotelManagerCreateReq);

    int deleteOrganizerManager(OrganizerManagerDeleteReq organizerManagerDeleteReq);

    int deleteHotelManager(HotelManagerDeleteReq hotelManagerDeleteReq);
}
