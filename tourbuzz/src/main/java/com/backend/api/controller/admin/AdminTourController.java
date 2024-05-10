package com.backend.api.controller.admin;

import com.backend.api.entity.TourEntity;
import com.backend.api.filter.AdminTourFilter;
import com.backend.api.request.admin.TourCreateReq;
import com.backend.api.request.admin.TourUpdateReq;
import com.backend.api.response.PaginatedRes;
import com.backend.api.response.TourListRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.TourService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/tours")
public class AdminTourController {
    private final AuthService authService;
    private final PaginationService paginationService;
    private final TourService tourService;

    @Tag(name = "Create Tour")
    @PostMapping("")
    public void createTour(@Valid @RequestBody TourCreateReq tourCreateReq) throws Exception {
        authService.verifyAdminToken();
        tourService.createTour(tourCreateReq);
    }

    @Tag(name = "Get Tour List")
    @GetMapping("")
    public PaginatedRes getTours(@Valid AdminTourFilter filter) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(filter.getPageNo(), filter.getPageSize());
        List<TourListRes> tourEntities = tourService.getTours();
        Page<TourListRes> pageInfo = (Page<TourListRes>) tourEntities;
        return paginationService.getPaginatedData(pageInfo, filter.getPageNo(), filter.getPageSize());
    }

    @Tag(name = "Update Tour")
    @PutMapping("")
    public void updateTour(@Valid @RequestBody TourUpdateReq tourUpdateReq) throws Exception {
        authService.verifyAdminToken();
        tourService.updateTour(tourUpdateReq);
    }

    @Tag(name = "Get Tour By ID")
    @GetMapping("/{id}")
    public TourEntity getTourById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return tourService.getTourById(id);
    }

    @Tag(name = "Delete Tour By ID")
    @DeleteMapping("/{id}")
    public void deleteTourById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        tourService.deleteTourById(id);
    }
}
