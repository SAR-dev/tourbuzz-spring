package com.backend.api.service;

import com.backend.api.ErrorMessages;
import com.backend.api.entity.ArticleEntity;
import com.backend.api.repository.postgres.ArticleRepository;
import com.backend.api.request.admin.ArticleCreateReq;
import com.backend.api.request.admin.ArticleUpdateReq;
import com.backend.api.response.ArticleCategoryRes;
import com.backend.api.response.ArticleCheckRes;
import jakarta.xml.bind.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleCheckRes checkArticle(ArticleCreateReq articleCreateReq){
        return articleRepository.checkArticle(articleCreateReq);
    }

    @Transactional
    public void createArticle(ArticleCreateReq articleCreateReq) throws ValidationException {
        ArticleCheckRes articleCheckRes = checkArticle(articleCreateReq);
        List<String> messages = new ArrayList<>();
        if(articleCheckRes.isSlug_exists()){
            messages.add(ErrorMessages.SLUG_EXISTS);
        }
        if(articleCheckRes.isTitle_exists()){
            messages.add(ErrorMessages.TITLE_EXISTS);
        }
        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
        articleRepository.createArticle(articleCreateReq);
    }

    public List<ArticleEntity> getArticles(){
        return articleRepository.getArticles();
    }

    public List<ArticleEntity> getLatestArticles(int count){
        return articleRepository.getLatestArticles(count);
    }

    public ArticleEntity getArticleById(int id){
        return articleRepository.getArticleById(id);
    }

    public ArticleEntity getArticleBySlug(String slug){
        return articleRepository.getArticleBySlug(slug);
    }

    public void deleteArticleById(int id) throws Exception {
        int count = articleRepository.deleteArticleById(id);
        if(count != 1){
            throw new Exception(ErrorMessages.DELETE_FAILED);
        }
    }

    public void updateArticle(ArticleUpdateReq articleUpdateReq) throws Exception {
        int count = articleRepository.updateArticle(articleUpdateReq);
        if(count != 1){
            throw new Exception(ErrorMessages.UPDATE_FAILED);
        }
    }

    public List<ArticleCategoryRes> getArticleCategories(){
        return articleRepository.getArticleCategories();
    }
}
