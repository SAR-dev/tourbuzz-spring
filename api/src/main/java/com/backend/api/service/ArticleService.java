package com.backend.api.service;

import com.backend.api.repository.postgres.ArticleRepository;
import com.backend.api.repository.postgres.WriterRepository;
import com.backend.api.request.ArticleCreateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.response.ArticleCheckRes;
import com.backend.api.response.WriterCheckRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleCheckRes checkArticle(ArticleCreateReq articleCreateReq){
        return articleRepository.checkArticle(articleCreateReq);
    }

    public void createArticle(ArticleCreateReq articleCreateReq){
        articleRepository.createArticle(articleCreateReq);
    }
}
