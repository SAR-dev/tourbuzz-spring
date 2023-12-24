package com.backend.api.controller;

import com.backend.api.entity.ImageEntity;
import com.backend.api.request.ArticleCreateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.response.ArticleCheckRes;
import com.backend.api.response.WriterCheckRes;
import com.backend.api.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final ImageService imageService;
    private final WriterService writerService;
    private final ArticleService articleService;
    private final AuthService authService;

//    Images API
    @PostMapping("/image")
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

//    Writers API
    @PostMapping("/writers/check")
    public WriterCheckRes checkWriter(@RequestBody WriterCreateReq writerCreateReq) throws Exception {
        authService.verifyAdminToken();
        return writerService.checkWriter(writerCreateReq);
    }

    @PostMapping("/writers")
    public void createWriter(@RequestBody WriterCreateReq writerCreateReq) throws Exception {
        authService.verifyAdminToken();
        writerService.createWriter(writerCreateReq);
    }

//    Article API
    @PostMapping("/article/check")
    public ArticleCheckRes checkArticle(@RequestBody ArticleCreateReq articleCreateReq) throws Exception {
        authService.verifyAdminToken();
        return articleService.checkArticle(articleCreateReq);
    }

    @PostMapping("/article")
    public void createArticle(@RequestBody ArticleCreateReq articleCreateReq) throws Exception {
        authService.verifyAdminToken();
        articleService.createArticle(articleCreateReq);
    }
}
