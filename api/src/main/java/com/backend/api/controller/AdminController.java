package com.backend.api.controller;

import com.backend.api.entity.ArticleEntity;
import com.backend.api.entity.ImageEntity;
import com.backend.api.entity.WriterEntity;
import com.backend.api.request.ArticleCreateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final ImageService imageService;
    private final WriterService writerService;
    private final ArticleService articleService;
    private final AuthService authService;
    private final PaginationService paginationService;

//    Images API
    @Tag(name = "Upload Image")
    @PostMapping(value = "/image",  consumes = {"multipart/form-data"})
    public ImageEntity uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        authService.verifyAdminToken();
        try {
            if (file.isEmpty()) {
                throw new Exception("Please select a file to upload");
            }

            byte[] imageData = file.getBytes();
            String imageName = file.getOriginalFilename();

            return imageService.uploadImage(imageData, imageName);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Tag(name = "Get Images List")
    @GetMapping("/images")
    public PaginatedRes getImages(
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();

        PageHelper.startPage(pageNo, pageSize);
        List<ImageEntity> imageEntities = imageService.getImages();
        Page<ImageEntity> pageInfo = (Page<ImageEntity>) imageEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

//    Writers API
    @Tag(name = "Create Writer")
    @PostMapping("/writers")
    public void createWriter(@RequestBody WriterCreateReq writerCreateReq) throws Exception {
        authService.verifyAdminToken();
        writerService.createWriter(writerCreateReq);
    }

    @Tag(name = "Get Writers List")
    @GetMapping("/writers")
    public PaginatedRes getWriters(
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();

        PageHelper.startPage(pageNo, pageSize);
        List<WriterEntity> writerEntities = writerService.getWriters();
        Page<WriterEntity> pageInfo = (Page<WriterEntity>) writerEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

//    Article API
    @Tag(name = "Create Article")
    @PostMapping("/article")
    public void createArticle(@RequestBody ArticleCreateReq articleCreateReq) throws Exception {
        authService.verifyAdminToken();
        articleService.createArticle(articleCreateReq);
    }

    @Tag(name = "Get Articles List")
    @GetMapping("/articles")
    public PaginatedRes getArticles(
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();

        PageHelper.startPage(pageNo, pageSize);
        List<ArticleEntity> articleEntities = articleService.getArticles();
        Page<ArticleEntity> pageInfo = (Page<ArticleEntity>) articleEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }
}
