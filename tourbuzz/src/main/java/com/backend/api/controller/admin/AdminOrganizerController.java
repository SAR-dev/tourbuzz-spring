package com.backend.api.controller.admin;

import com.backend.api.entity.OrganizerEntity;
import com.backend.api.filter.AdminOrganizerFilter;
import com.backend.api.request.admin.OrganizerCreateReq;
import com.backend.api.request.admin.OrganizerUpdateReq;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.OrganizerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/organizers")
public class AdminOrganizerController {
    private final AuthService authService;
    private final PaginationService paginationService;
    private final OrganizerService organizerService;

    @Tag(name = "Create Organizer")
    @PostMapping("")
    public void createOrganizer(@Valid @RequestBody OrganizerCreateReq organizerCreateReq) throws Exception {
        authService.verifyAdminToken();
        organizerService.createOrganizer(organizerCreateReq);
    }

    @Tag(name = "Get Organizer List")
    @GetMapping("")
    public PaginatedRes getOrganizers(@Valid AdminOrganizerFilter filter) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(filter.getPageNo(), filter.getPageSize());
        List<OrganizerEntity> organizerEntities = organizerService.getOrganizers(filter.getName(), filter.getEmail());
        Page<OrganizerEntity> pageInfo = (Page<OrganizerEntity>) organizerEntities;
        return paginationService.getPaginatedData(pageInfo, filter.getPageNo(), filter.getPageSize());
    }

    @Tag(name = "Update Organizer")
    @PutMapping("")
    public void updateOrganizer(@Valid @RequestBody OrganizerUpdateReq organizerUpdateReq) throws Exception {
        authService.verifyAdminToken();
        organizerService.updateOrganizer(organizerUpdateReq);
    }

    @Tag(name = "Get Organizer By ID")
    @GetMapping("/{id}")
    public OrganizerEntity getOrganizerById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return organizerService.getOrganizerById(id);
    }

    @Tag(name = "Delete Organizer By ID")
    @DeleteMapping("/{id}")
    public void deleteOrganizerById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        organizerService.deleteOrganizerById(id);
    }
}
