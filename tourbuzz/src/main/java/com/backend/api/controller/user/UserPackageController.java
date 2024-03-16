package com.backend.api.controller.user;

import com.backend.api.entity.EnquiryEntity;
import com.backend.api.entity.PackageEntity;
import com.backend.api.response.TourPackagePurchaseRes;
import com.backend.api.service.PackageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/packages")
public class UserPackageController {
    private final PackageService packageService;

    @Tag(name = "Get Package By ID")
    @GetMapping("/{id}")
    public TourPackagePurchaseRes getPackageById(@PathVariable int id) throws NotFoundException {
        return packageService.getPackageById(id);
    }
}
