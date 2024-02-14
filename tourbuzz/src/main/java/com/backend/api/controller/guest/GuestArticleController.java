package com.backend.api.controller.guest;

import com.backend.api.entity.ArticleEntity;
import com.backend.api.entity.TourEntity;
import com.backend.api.service.ArticleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/articles")
public class GuestArticleController {
    private final ArticleService articleService;

    @Tag(name = "Latest Articles")
    @GetMapping("/latest/{count}")
    public List<ArticleEntity> getLatestArticles(@PathVariable int count) {
        return articleService.getLatestArticles(count);
    }

    @Tag(name = "Get Article By Slug")
    @GetMapping("/{slug}")
    public ArticleEntity getArticleBySlug(@PathVariable String slug) {
        return articleService.getArticleBySlug(slug);
    }

}
