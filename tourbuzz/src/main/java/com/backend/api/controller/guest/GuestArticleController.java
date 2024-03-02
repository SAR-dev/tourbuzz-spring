package com.backend.api.controller.guest;

import com.backend.api.entity.ArticleEntity;
import com.backend.api.entity.TourEntity;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.ArticleService;
import com.backend.api.service.PaginationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/articles")
public class GuestArticleController {
    private final ArticleService articleService;
    private final PaginationService paginationService;

    @Tag(name = "Get Articles List")
    @GetMapping("")
    public PaginatedRes getArticles(
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        List<ArticleEntity> articleEntities = articleService.getArticles();
        Page<ArticleEntity> pageInfo = (Page<ArticleEntity>) articleEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Latest Articles")
    @GetMapping("/latest/{count}")
    public List<ArticleEntity> getLatestArticles(@PathVariable int count) {
        return articleService.getLatestArticles(count);
    }

    @Tag(name = "Get Article By Slug")
    @GetMapping("/{slug}")
    public ArticleEntity getArticleBySlug(@PathVariable String slug) throws NotFoundException {
        return articleService.getArticleBySlug(slug);
    }

}
