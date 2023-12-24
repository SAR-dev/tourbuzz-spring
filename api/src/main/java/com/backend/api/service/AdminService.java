package com.backend.api.service;

import com.backend.api.repository.postgres.AdminRepository;
import com.backend.api.request.ArticleCreateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.response.ArticleCheckRes;
import com.backend.api.response.WriterCheckRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public WriterCheckRes checkWriter(WriterCreateReq writerCreateReq){
        return adminRepository.checkWriter(writerCreateReq);
    }

    public void createWriter(WriterCreateReq createReq){
        adminRepository.createWriter(createReq);
    }

    public ArticleCheckRes checkArticle(ArticleCreateReq articleCreateReq){
        return adminRepository.checkArticle(articleCreateReq);
    }

    public void createArticle(ArticleCreateReq articleCreateReq){
        adminRepository.createArticle(articleCreateReq);
    }
}
