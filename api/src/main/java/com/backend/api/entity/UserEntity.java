package com.backend.api.entity;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Objects;

@Data
public class UserEntity {
    private int id;
    private String email;
    private String username;
    private String name;
    private int images_id;
    private Timestamp last_logged_in;
    private Timestamp created_at;
    private Timestamp updated_at;

    private ImageEntity avatar;

    public ImageEntity getAvatar() {
        if(Objects.isNull(avatar)){
            ImageEntity imageEntity = new ImageEntity();
            imageEntity.setId(0);
            imageEntity.setOptimized_url("https://api.dicebear.com/7.x/adventurer-neutral/svg?seed=" + email);
            imageEntity.setSource_url("https://api.dicebear.com/7.x/adventurer-neutral/svg?seed=" + email);
            imageEntity.setThumbnail_url("https://api.dicebear.com/7.x/adventurer-neutral/svg?seed=" + email);
            return imageEntity;
        } else {
            return avatar;
        }
    }
}
