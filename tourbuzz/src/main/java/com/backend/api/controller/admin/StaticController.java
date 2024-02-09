package com.backend.api.controller.admin;

import com.backend.api.entity.DistrictEntity;
import com.backend.api.entity.WriterEntity;
import com.backend.api.request.admin.WriterCreateReq;
import com.backend.api.request.admin.WriterUpdateReq;
import com.backend.api.response.DivisionWithDistrictsRes;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.StaticService;
import com.backend.api.service.admin.WriterService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class StaticController {
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
