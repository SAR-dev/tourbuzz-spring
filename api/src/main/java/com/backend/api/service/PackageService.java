package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.PackageEntity;
import com.backend.api.repository.postgres.PackageRepository;
import com.backend.api.request.TourPackageCreatePayload;
import com.backend.api.request.TourPackageCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PackageService {
    private final PackageRepository packageRepository;

    public void createPackages(int toursId, List<TourPackageCreatePayload> tourPackageCreatePayloads){
        packageRepository.createPackages(toursId, tourPackageCreatePayloads);
    }

    public void createDraftPackage(TourPackageCreateReq tourPackageCreateReq) throws Exception {
        PackageEntity packageEntity = getPackageByTourIdAndTitle(tourPackageCreateReq.getToursId(), tourPackageCreateReq.getTitle());
        if(!Objects.isNull(packageEntity)){
            throw new Exception(ErrorMessages.NON_UNIQUE_TOUR_PACKAGE_TITLE);
        }
        packageRepository.createDraftPackage(tourPackageCreateReq);
    }

    public PackageEntity getPackageByTourIdAndTitle(int toursId, String title){
        return packageRepository.getPackageByTourIdAndTitle(toursId, title);
    }

    public void deletePackageById(int id) throws Exception {
        int count = packageRepository.deletePackageById(id);
        if(count != 1){
            throw new Exception(ErrorMessages.DELETE_FAILED);
        }
    }

    public List<Integer> getPackageIdsByTourId(int toursId){
        return packageRepository.getPackageIdsByTourId(toursId);
    }
}
