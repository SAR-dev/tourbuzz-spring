package com.backend.api.repository.postgres;

import com.backend.api.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    UserEntity createUserByEmail(String email);

    UserEntity getUserByEmail(String email);
}
