package com.backend.api.controller;

import com.backend.api.request.ArticleCreateReq;
import com.backend.api.request.TourCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/tour")
    public void createArticle(ArticleCreateReq createReq) {

    }
}
