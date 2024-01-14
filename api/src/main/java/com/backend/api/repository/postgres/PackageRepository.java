package com.backend.api.repository.postgres;

import com.backend.api.entity.PackageEntity;
import com.backend.api.request.TourPackageCreatePayload;
import com.backend.api.request.TourPackageCreateReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PackageRepository {
    void createPackages(@Param("toursId") int toursId, @Param("tourPackages") List<TourPackageCreatePayload> tourPackageCreatePayloads);

    void createPackage(TourPackageCreateReq tourPackageCreateReq);

    PackageEntity getPackageByTourIdAndTitle(int toursId, String title);

    int deletePackageById(int id);
}
