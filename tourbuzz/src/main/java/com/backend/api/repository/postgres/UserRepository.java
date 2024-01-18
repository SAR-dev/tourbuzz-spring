package com.backend.api.repository.postgres;

import com.backend.api.entity.UserEntity;
import com.backend.api.request.admin.UserUpdateReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    void createUserByEmail(String email);

    UserEntity getUserByEmail(String email);

    int updateUserByEmail(UserUpdateReq userUpdateReq);
}
