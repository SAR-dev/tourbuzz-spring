package com.backend.api.repository.sqlite;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenRepository {
    String getRandomKey();

    int countKey(String key);

    void createKey(String key, String email, String value);

    String getValueByKeyAndEmail(String key, String email);
}
