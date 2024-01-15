package com.backend.api.repository.postgres;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminRepository {
    void updateSecret();

    String getSecret();
}
