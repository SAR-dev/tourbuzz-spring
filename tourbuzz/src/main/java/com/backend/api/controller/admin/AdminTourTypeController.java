package com.backend.api.controller.admin;

import com.backend.api.entity.TourTypeEntity;
import com.backend.api.filter.AdminTourTypeFilter;
import com.backend.api.request.admin.TourTypeCreateReq;
import com.backend.api.request.admin.TourTypeUpdateReq;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.TourTypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/tour-types")
public class AdminTourTypeController {
    private final AuthService authService;
    private final PaginationService paginationService;
    private final TourTypeService tourTypeService;

    @Tag(name = "Create Tour Type")
    @PostMapping("")
    public void createTourType(@Valid @RequestBody TourTypeCreateReq tourTypeCreateReq) throws Exception {
        authService.verifyAdminToken();
        tourTypeService.createTourType(tourTypeCreateReq);
    }

    @Tag(name = "Get Tour Type List")
    @GetMapping("")
    public PaginatedRes getTourTypes(@Valid AdminTourTypeFilter filter) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(filter.getPageNo(), filter.getPageSize());
        List<TourTypeEntity> tourTypeEntities = tourTypeService.getTourTypes(filter.getName());
        Page<TourTypeEntity> pageInfo = (Page<TourTypeEntity>) tourTypeEntities;
        return paginationService.getPaginatedData(pageInfo, filter.getPageNo(), filter.getPageSize());
    }

    @Tag(name = "Update Tour Type")
    @PutMapping("")
    public void updateTourType(@Valid @RequestBody TourTypeUpdateReq tourTypeUpdateReq) throws Exception {
        authService.verifyAdminToken();
        tourTypeService.updateTourType(tourTypeUpdateReq);
    }

    @Tag(name = "Get Tour Type By ID")
    @GetMapping("/{id}")
    public TourTypeEntity getTourTypeById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return tourTypeService.getTourTypeById(id);
    }

    @Tag(name = "Delete Tour Type By ID")
    @DeleteMapping("/{id}")
    public void deleteTourTypeById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        tourTypeService.deleteTourTypeById(id);
    }
}
