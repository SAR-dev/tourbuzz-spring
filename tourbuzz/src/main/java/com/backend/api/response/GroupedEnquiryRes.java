package com.backend.api.response;

import com.backend.api.entity.EnquiryEntity;
import com.backend.api.entity.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class GroupedEnquiryRes {
    private UserEntity user;
    private List<EnquiryEntity> enquiries;
}
