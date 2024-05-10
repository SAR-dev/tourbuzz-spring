package com.backend.api.controller.admin;

import com.backend.api.entity.ImageEntity;
import com.backend.api.filter.AdminImageFilter;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.ImageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/images")
public class AdminImageController {
    private final AuthService authService;
    private final PaginationService paginationService;
    private final ImageService imageService;

    @Tag(name = "Upload Image")
    @PostMapping(value = "",  consumes = {"multipart/form-data"})
    public ImageEntity uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) throws Exception {
        authService.verifyAdminToken();
        try {
            if (file.isEmpty()) {
                throw new Exception("Please select a file to upload");
            }

            byte[] imageData = file.getBytes();

            return imageService.uploadImage(imageData, title);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Tag(name = "Get Images List")
    @GetMapping("")
    public PaginatedRes getImages(@Valid AdminImageFilter filter) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(filter.getPageNo(), filter.getPageSize());
        List<ImageEntity> imageEntities = imageService.getImages();
        Page<ImageEntity> pageInfo = (Page<ImageEntity>) imageEntities;
        return paginationService.getPaginatedData(pageInfo, filter.getPageNo(), filter.getPageSize());
    }

    @Tag(name = "Delete Image")
    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        imageService.deleteImage(id);
    }
}
