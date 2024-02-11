package com.backend.api.service.admin;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.AdEntity;
import com.backend.api.repository.postgres.AdRepository;
import com.backend.api.request.admin.AdCreateReq;
import com.backend.api.request.admin.AdUpdateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdService {
    private final AdRepository adRepository;

    public void createAd(AdCreateReq adCreateReq){
        adRepository.createAd(adCreateReq);
    }

    public List<AdEntity> getAds(){
        return adRepository.getAds();
    }

    public AdEntity getAdById(int id){
        return adRepository.getAdById(id);
    }

    public void deleteAdById(int id) throws Exception {
        int count = adRepository.deleteAdById(id);
        if(count != 1){
            throw new Exception(ErrorMessages.DELETE_FAILED);
        }
    }

    public void updateAd(AdUpdateReq adUpdateReq) throws Exception {
        int count = adRepository.updateAd(adUpdateReq);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }
}
