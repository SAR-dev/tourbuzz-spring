package com.backend.api.controller.admin;

import com.backend.api.entity.TourTypeEntity;
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
    public PaginatedRes getTourTypes(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<TourTypeEntity> tourTypeEntities = tourTypeService.getTourTypes(name);
        Page<TourTypeEntity> pageInfo = (Page<TourTypeEntity>) tourTypeEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
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
