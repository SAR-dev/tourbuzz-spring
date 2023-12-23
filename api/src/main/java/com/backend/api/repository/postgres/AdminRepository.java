package com.backend.api.repository.postgres;

import com.backend.api.entity.UserEntity;
import com.backend.api.request.ArticleCreateReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminRepository {
    void createArticle(ArticleCreateReq createReq);
}
