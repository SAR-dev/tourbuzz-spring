package com.backend.api.controller;

import com.backend.api.entity.ImageEntity;
import com.backend.api.request.ArticleCreateReq;
import com.backend.api.request.TourCreateReq;
import com.backend.api.request.WriterCreateReq;
import com.backend.api.service.AdminService;
import com.backend.api.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final ImageUploadService imageUploadService;
    private final AdminService adminService;

    @PostMapping("/image")
    public ImageEntity uploadImage(@RequestParam("file") MultipartFile file) throws Exception {
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

    @PostMapping("/writers")
    public void createWriter(@RequestBody WriterCreateReq request){
        adminService.createWriter(request);
    }

    @GetMapping("/tour")
    public void createArticle(@RequestBody ArticleCreateReq createReq) {

    }
}
