package com.backend.api.repository.postgres;

import com.backend.api.request.ArticleCreateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.response.ArticleCheckRes;
import com.backend.api.response.WriterCheckRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminRepository {
    WriterCheckRes checkWriter(WriterCreateReq writerCreateReq);

    void createWriter(WriterCreateReq writerCreateReq);

    ArticleCheckRes checkArticle(ArticleCreateReq articleCreateReq);

    void createArticle(ArticleCreateReq articleCreateReq);
}
