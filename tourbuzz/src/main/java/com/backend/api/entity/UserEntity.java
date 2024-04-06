package com.backend.api.entity;

import com.backend.api.Constants;
import com.backend.api.ErrorMessages;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;

@Data
public class UserEntity {
    private int id;
    private String email;
    private String username;
    private String name;
    private String contact_no;
    private String contact_email;
    private String residence;
    private String facebook_id;
    private String youtube_id;
    private String twitter_id;
    private int images_id;
    private Timestamp created_at;
    private Timestamp updated_at;

    private ImageEntity avatar;

    public ImageEntity getAvatar() {
        if(Objects.isNull(avatar)){
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setId(0);
            imageEntity.setOptimized_url(Constants.AVATAR_GENERATOR_BASE_URL + email);
            imageEntity.setSource_url(Constants.AVATAR_GENERATOR_BASE_URL + email);
            imageEntity.setThumbnail_url(Constants.AVATAR_GENERATOR_BASE_URL + email);
            return imageEntity;
        } else {
            return avatar;
        }
    }
}
