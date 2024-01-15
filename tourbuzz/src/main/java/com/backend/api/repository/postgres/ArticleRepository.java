

package com.backend.api.repository.postgres;

import com.backend.api.entity.ArticleEntity;
import com.backend.api.entity.WriterEntity;
import com.backend.api.request.ArticleCreateReq;
import com.backend.api.request.ArticleUpdateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.request.WriterUpdateReq;
import com.backend.api.response.ArticleCheckRes;
import com.backend.api.response.WriterCheckRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleRepository {
    ArticleCheckRes checkArticle(ArticleCreateReq articleCreateReq);

    void createArticle(ArticleCreateReq articleCreateReq);

    List<ArticleEntity> getArticles();

    ArticleEntity getArticleById(int id);

    int updateArticle(ArticleUpdateReq articleUpdateReq);

    int deleteArticleById(int id);
}
