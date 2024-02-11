package com.backend.api.response;

import com.backend.api.entity.EmojiReactionEntity;
import com.backend.api.entity.ImageEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class TourListRes {
    private int id;
    private int tour_types_id;
    private int organizers_id;
    private int images_id;
    private String title;
    private String video_link;
    private String slug;
    private String highlights;
    private String group_size;
    private String group_info;
    private String transports;
    private Timestamp start_at;
    private Timestamp finish_at;
    private Timestamp last_sell_at;
    private String image_ids;

    private String tour_type_name;
    private String organizer_name;
    private String organizer_verification;

    @Getter(AccessLevel.NONE)
    private String place_names;
    private List<String> place_names_list;
    @Getter(AccessLevel.NONE)
    private String district_names;
    private List<String> district_names_list;
    @Getter(AccessLevel.NONE)
    private String package_prices;
    private List<Integer> package_prices_list;

    private List<ImageEntity> images;
    private List<EmojiReactionEntity> emoji_reactions;
    private ImageEntity avatar;

    private boolean expired;

    public boolean isExpired() {
        return getLast_sell_at().before(new Date());
    }


    public List<String> getPlace_names_list() {
        return Arrays.stream(place_names.split(",")).toList();
    }

    public List<String> getDistrict_names_list() {
        return Arrays.stream(district_names.split(",")).toList();
    }

    public List<Integer> getPackage_prices_list() {
        return Arrays.stream(package_prices.split(",")).map(String::trim).map(Integer::parseInt).toList();
    }
}
