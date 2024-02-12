package com.backend.api.controller.admin;

import com.backend.api.entity.AdEntity;
import com.backend.api.request.admin.AdCreateReq;
import com.backend.api.request.admin.AdUpdateReq;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.AdService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/ads")
public class AdminAdController {
    private final AuthService authService;
    private final PaginationService paginationService;
    private final AdService adService;

    @Tag(name = "Create Ad")
    @PostMapping("")
    public void createAd(@Valid @RequestBody AdCreateReq adCreateReq) throws Exception {
        authService.verifyAdminToken();
        adService.createAd(adCreateReq);
    }

    @Tag(name = "Get Ad List")
    @GetMapping("")
    public PaginatedRes getAds(
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<AdEntity> adEntities = adService.getAds();
        Page<AdEntity> pageInfo = (Page<AdEntity>) adEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Update Ad")
    @PutMapping("")
    public void updateAd(@Valid @RequestBody AdUpdateReq adUpdateReq) throws Exception {
        authService.verifyAdminToken();
        adService.updateAd(adUpdateReq);
    }

    @Tag(name = "Get Ad By ID")
    @GetMapping("/{id}")
    public AdEntity getAdById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return adService.getAdById(id);
    }

    @Tag(name = "Delete Ad By ID")
    @DeleteMapping("/{id}")
    public void deleteAdById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        adService.deleteAdById(id);
    }
}
