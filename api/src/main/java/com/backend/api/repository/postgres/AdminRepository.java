package com.backend.api.repository.postgres;

import com.backend.api.request.ArticleCreateReq;
import com.backend.api.request.WriterCreateReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminRepository {
    void createArticle(ArticleCreateReq createReq);

    void createWriter(WriterCreateReq createReq);
}
