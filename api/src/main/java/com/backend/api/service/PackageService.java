package com.backend.api.service;

import com.backend.api.repository.postgres.PackageRepository;
import com.backend.api.request.TourPackageCreatePayload;
import com.backend.api.request.TourPackageCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageService {
    private final PackageRepository packageRepository;

    public void createPackages(int toursId, List<TourPackageCreatePayload> tourPackageCreatePayloads){
        packageRepository.createPackages(toursId, tourPackageCreatePayloads);
    }

    public void createPackage(TourPackageCreateReq tourPackageCreateReq){
        packageRepository.createPackage(tourPackageCreateReq);
    }
}
