package com.backend.api.controller;

import com.backend.api.entity.ImageEntity;
import com.backend.api.request.ArticleCreateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.response.ArticleCheckRes;
import com.backend.api.response.WriterCheckRes;
import com.backend.api.service.AdminService;
import com.backend.api.service.AuthService;
import com.backend.api.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final ImageUploadService imageUploadService;
    private final AdminService adminService;
    private final AuthService authService;

    @PostMapping("/image")
    public ImageEntity uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
        authService.verifyAdminToken();
        try {
            if (file.isEmpty()) {
                throw new Exception("Please select a file to upload");
            }

            byte[] imageData = file.getBytes();
            String imageName = file.getOriginalFilename();

            return imageUploadService.uploadImage(imageData, imageName);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/writers/check")
    public WriterCheckRes checkWriter(@RequestBody WriterCreateReq writerCreateReq) throws Exception {
        authService.verifyAdminToken();
        return adminService.checkWriter(writerCreateReq);
    }

    @PostMapping("/writers")
    public void createWriter(@RequestBody WriterCreateReq writerCreateReq) throws Exception {
        authService.verifyAdminToken();
        adminService.createWriter(writerCreateReq);
    }

    @PostMapping("/article/check")
    public ArticleCheckRes checkArticle(@RequestBody ArticleCreateReq articleCreateReq) throws Exception {
        authService.verifyAdminToken();
        return adminService.checkArticle(articleCreateReq);
    }

    @PostMapping("/article")
    public void createArticle(@RequestBody ArticleCreateReq articleCreateReq) throws Exception {
        authService.verifyAdminToken();
        adminService.createArticle(articleCreateReq);
    }
}
