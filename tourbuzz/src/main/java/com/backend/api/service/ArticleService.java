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
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public ArticleEntity getArticleBySlug(String slug) throws NotFoundException {
        ArticleEntity article = articleRepository.getArticleBySlug(slug);
        if (Objects.isNull(article)) {
            throw new NotFoundException(ErrorMessages.NOT_FOUND);
        }
        return article;
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
