package com.backend.api.repository.postgres;

import com.backend.api.entity.PackageEntity;
import com.backend.api.request.admin.TourPackageCreatePayload;
import com.backend.api.request.admin.TourPackageCreateReq;
import com.backend.api.request.admin.TourPackageUpdatePayload;
import com.backend.api.response.TourPackagePurchaseRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PackageRepository {
    void createPackages(@Param("toursId") int toursId, @Param("tourPackages") List<TourPackageCreatePayload> tourPackageCreatePayloads);

    void createDraftPackage(TourPackageCreateReq tourPackageCreateReq);

    int updatePackages(@Param("toursId") int toursId, @Param("tourPackages") List<TourPackageUpdatePayload> tourPackageUpdatePayloads);

    PackageEntity getPackageByTourIdAndTitle(int toursId, String title);

    TourPackagePurchaseRes getPackageById(int id);

    int deletePackageById(int id);

    List<Integer> getPackageIdsByTourId(int toursId);
}
