package com.backend.api.response;

import com.backend.api.entity.ImageEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TourListRes {
    private int id;
    private int tourTypesId;
    private int organizersId;
    private int imagesId;
    private String title;
    private String videoLink;
    private String slug;
    private String highlights;
    private String groupSize;
    private String groupInfo;
    private String transports;
    private Timestamp startAt;
    private Timestamp finishAt;
    private Timestamp lastSellAt;
    private String imageIds;

    private String tourTypeName;
    private String organizerName;
    private String organizerVerification;

    @Getter(AccessLevel.NONE)
    private String placeNames;
    private List<String> placeNamesList;
    @Getter(AccessLevel.NONE)
    private String districtNames;
    private List<String> districtNamesList;
    @Getter(AccessLevel.NONE)
    private String packagePrices;
    private List<Integer> packagePricesList;

    private List<ImageEntity> images;
    private ImageEntity avatar;

    private boolean expired;

    public boolean isExpired() {
        return getLastSellAt().before(new Date());
    }


    public List<String> getPlaceNamesList() {
        return Arrays.stream(placeNames.split(",")).toList();
    }

    public List<String> getDistrictNamesList() {
        return Arrays.stream(districtNames.split(",")).toList();
    }

    public List<Integer> getPackagePricesList() {
        return Arrays.stream(packagePrices.split(",")).map(String::trim).map(Integer::parseInt).toList();
    }
}
