package com.backend.api.controller.admin;

import com.backend.api.entity.OrganizerEntity;
import com.backend.api.request.admin.OrganizerCreateReq;
import com.backend.api.request.admin.OrganizerUpdateReq;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.admin.OrganizerService;
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
    public PaginatedRes getOrganizers(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<OrganizerEntity> organizerEntities = organizerService.getOrganizers(name, email);
        Page<OrganizerEntity> pageInfo = (Page<OrganizerEntity>) organizerEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
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
