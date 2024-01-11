package com.backend.api.response;

import com.backend.api.entity.ImageEntity;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Data
public class TourListRes {
    private int id;
    private int tour_types_id;
    private int organizers_id;
    private int images_id;
    private String title;
    private String slug;
    private String md_desc;
    private String summary;
    private String group_size;
    private String group_info;
    private String services;
    private String transports;
    private String cancel_ins;
    private String start_ins;
    private String finish_ins;
    private Timestamp start_at;
    private Timestamp finish_at;
    private Timestamp last_sell_at;
    private String image_ids;
    private Timestamp created_at;
    private Timestamp updated_at;

    private String tour_type_name;
    private String organizer_name;
    private String organizer_verification;

    private String place_names;
    private List<String> place_names_list;
    private String district_names;
    private List<String> district_names_list;
    private String package_prices;
    private List<Integer> package_prices_list;

    private List<ImageEntity> images;
    private ImageEntity avatar;

    public List<String> getPlace_names_list() {
        return Arrays.stream(place_names.split(",")).toList();
    }

    public List<String> getDistrict_names_list() {
        return Arrays.stream(district_names.split(",")).toList();
    }

    public List<Integer> getPackage_prices_list() {
        return Arrays.stream(package_prices.split(",")).map(Integer::parseInt).toList();
    }
}
