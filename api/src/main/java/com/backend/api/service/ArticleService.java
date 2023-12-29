package com.backend.api.service;

import com.backend.api.entity.ArticleEntity;
import com.backend.api.entity.WriterEntity;
import com.backend.api.repository.postgres.ArticleRepository;
import com.backend.api.repository.postgres.WriterRepository;
import com.backend.api.request.ArticleCreateReq;
import com.backend.api.request.ArticleUpdateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.request.WriterUpdateReq;
import com.backend.api.response.ArticleCheckRes;
import com.backend.api.response.WriterCheckRes;
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
            messages.add("Slug already exists.");
        }
        if(articleCheckRes.isTitle_exists()){
            messages.add("Title already exists.");
        }
        if(!messages.isEmpty()){
            throw new ValidationException(String.join("", messages));
        }
        articleRepository.createArticle(articleCreateReq);
    }

    public List<ArticleEntity> getArticles(){
        return articleRepository.getArticles();
    }

    public ArticleEntity getArticleById(int id){
        return articleRepository.getArticleById(id);
    }

    public void deleteArticleById(int id) throws Exception {
        int count = articleRepository.deleteArticleById(id);
        if(count != 1){
            throw new Exception("Article was not be deleted");
        }
    }

    public void updateArticle(ArticleUpdateReq articleUpdateReq) throws Exception {
        int count = articleRepository.updateArticle(articleUpdateReq);
        if(count != 1){
            throw new Exception("Article could not be updated");
        }
    }
}
