

package com.backend.api.repository.postgres.admin;

import com.backend.api.entity.ArticleEntity;
import com.backend.api.entity.WriterEntity;
import com.backend.api.request.admin.ArticleCreateReq;
import com.backend.api.request.admin.ArticleUpdateReq;
import com.backend.api.request.admin.WriterCreateReq;
import com.backend.api.request.admin.WriterUpdateReq;
import com.backend.api.response.ArticleCategoryRes;
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

    List<ArticleCategoryRes> getArticleCategories();
}
