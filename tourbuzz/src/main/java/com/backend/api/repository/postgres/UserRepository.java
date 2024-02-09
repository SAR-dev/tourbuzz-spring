package com.backend.api.repository.postgres;

import com.backend.api.entity.UserEntity;
import com.backend.api.request.admin.UserUpdateReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserRepository {
    String createUserByEmail(String email);

    UserEntity getUserByEmail(String email);

    int updateUserByEmail(@Param("data") UserUpdateReq userUpdateReq, String email);
}
