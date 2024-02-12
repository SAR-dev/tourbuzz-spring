package com.backend.api.controller.admin;

import com.backend.api.entity.PackageEntity;
import com.backend.api.request.admin.TourPackageCreateReq;
import com.backend.api.service.AuthService;
import com.backend.api.service.PackageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/tour-packages")
public class AdminTourPackageController {
    private final AuthService authService;
    private final PackageService packageService;

    @Tag(name = "Create Tour Package")
    @PostMapping("")
    public PackageEntity createPackage(@Valid @RequestBody TourPackageCreateReq tourPackageCreateReq) throws Exception {
        authService.verifyAdminToken();
        packageService.createDraftPackage(tourPackageCreateReq);
        return packageService.getPackageByTourIdAndTitle(tourPackageCreateReq.getToursId(), tourPackageCreateReq.getTitle());
    }

    @Tag(name = "Delete Tour Package By ID")
    @DeleteMapping("/{id}")
    public void deletePackageById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        packageService.deletePackageById(id);
    }
}
