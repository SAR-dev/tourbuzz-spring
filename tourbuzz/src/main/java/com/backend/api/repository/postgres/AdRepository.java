package com.backend.api.repository.postgres;

import com.backend.api.entity.AdEntity;
import com.backend.api.request.AdCreateReq;
import com.backend.api.request.AdUpdateReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdRepository {
    void createAd(AdCreateReq adCreateReq);

    int updateAd(AdUpdateReq adUpdateReq);

    int deleteAdById(int id);

    List<AdEntity> getAds();

    AdEntity getAdById(int id);
}
