package com.backend.api.response;

import com.backend.api.entity.EnquiryEntity;
import com.backend.api.entity.UserEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GroupedEnquiryRes {
    private UserEntity user;
    private List<EnquiryEntity> enquiries;
}
