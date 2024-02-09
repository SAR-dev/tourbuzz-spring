package com.backend.api.controller.admin;

import com.backend.api.entity.PackageEntity;
import com.backend.api.entity.TourLogEntity;
import com.backend.api.request.admin.TourLogCreateReq;
import com.backend.api.request.admin.TourPackageCreateReq;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.admin.PackageService;
import com.backend.api.service.admin.TourLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/tour-logs")
public class TourLogsController {
    private final AuthService authService;
    private final TourLogService tourLogService;

    @Tag(name = "Create Tour Log")
    @PostMapping("")
    public TourLogEntity createTourLog(@Valid @RequestBody TourLogCreateReq tourLogCreateReq) throws Exception {
        authService.verifyAdminToken();
        tourLogService.createDraftTourLog(tourLogCreateReq);
        return tourLogService.getTourLogByTourIdAndDate(tourLogCreateReq.getToursId(), tourLogCreateReq.getDate());
    }

    @Tag(name = "Delete Tour Log By ID")
    @DeleteMapping("/{id}")
    public void deleteTourLogsById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        tourLogService.deleteTourLogsById(id);
    }
}
