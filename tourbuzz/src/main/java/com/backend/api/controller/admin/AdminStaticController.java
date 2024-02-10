package com.backend.api.controller.admin;

import com.backend.api.entity.DistrictEntity;
import com.backend.api.response.DivisionWithDistrictsRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.StaticService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminStaticController {
    private final AuthService authService;
    private final StaticService staticService;

    @Tag(name = "Get Divisions")
    @GetMapping("/divisions")
    public List<DivisionWithDistrictsRes> getDivisionsWithDistricts() throws Exception {
        authService.verifyAdminToken();
        return staticService.getDivisionsWithDistricts();
    }

    @Tag(name = "Get Districts")
    @GetMapping("/districts")
    public List<DistrictEntity> getDistricts() throws Exception {
        authService.verifyAdminToken();
        return staticService.getDistricts();
    }
}
