package com.backend.api.controller.admin;

import com.backend.api.entity.ArticleEntity;
import com.backend.api.filter.AdminArticleFilter;
import com.backend.api.request.admin.ArticleCreateReq;
import com.backend.api.request.admin.ArticleUpdateReq;
import com.backend.api.response.ArticleCategoryRes;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.ArticleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/articles")
public class AdminArticleController {
    private final AuthService authService;
    private final PaginationService paginationService;
    private final ArticleService articleService;

    @Tag(name = "Create Article")
    @PostMapping("")
    public void createArticle(@Valid @RequestBody ArticleCreateReq articleCreateReq) throws Exception {
        authService.verifyAdminToken();
        articleService.createArticle(articleCreateReq);
    }

    @Tag(name = "Get Articles List")
    @GetMapping("")
    public PaginatedRes getArticles(@Valid AdminArticleFilter filter) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(filter.getPageNo(), filter.getPageSize());
        List<ArticleEntity> articleEntities = articleService.getArticles();
        Page<ArticleEntity> pageInfo = (Page<ArticleEntity>) articleEntities;
        return paginationService.getPaginatedData(pageInfo, filter.getPageNo(), filter.getPageSize());
    }

    @Tag(name = "Get Article Categories")
    @GetMapping("/categories")
    public List<ArticleCategoryRes> getArticleCategories() throws Exception {
        authService.verifyAdminToken();
        return articleService.getArticleCategories();
    }

    @Tag(name = "Update Article")
    @PutMapping("")
    public void updateArticle(@Valid @RequestBody ArticleUpdateReq articleUpdateReq) throws Exception {
        authService.verifyAdminToken();
        articleService.updateArticle(articleUpdateReq);
    }

    @Tag(name = "Get Article By ID")
    @GetMapping("/{id}")
    public ArticleEntity getArticleById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return articleService.getArticleById(id);
    }

    @Tag(name = "Delete Article By ID")
    @DeleteMapping("/{id}")
    public void deleteArticleById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        articleService.deleteArticleById(id);
    }
}
