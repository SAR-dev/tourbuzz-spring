package com.backend.api.controller.admin;

import com.backend.api.request.admin.*;
import com.backend.api.service.AuthService;
import com.backend.api.service.ManagerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/managers")
public class AdminManagerController {
    private final ManagerService managerService;
    private final AuthService authService;

    @Tag(name = "Create Organizer Manager")
    @PostMapping("/organizers")
    public void createOrganizerManager(@Valid @RequestBody OrganizerManagerCreateReq organizerManagerCreateReq) throws Exception {
        authService.verifyAdminToken();
        managerService.createOrganizerManager(organizerManagerCreateReq);
    }

    @Tag(name = "Create Hotel Manager")
    @PostMapping("/hotels")
    public void createHotelManager(@Valid @RequestBody HotelManagerCreateReq hotelManagerCreateReq) throws Exception {
        authService.verifyAdminToken();
        managerService.createHotelManager(hotelManagerCreateReq);
    }

    @Tag(name = "Delete Organizer Manager")
    @PutMapping("/organizers")
    public void deleteOrganizerManager(@Valid @RequestBody OrganizerManagerDeleteReq organizerManagerDeleteReq) throws Exception {
        authService.verifyAdminToken();
        managerService.deleteOrganizerManager(organizerManagerDeleteReq);
    }

    @Tag(name = "Delete Hotel Manager")
    @PutMapping("/hotels")
    public void deleteHotelManager(@Valid @RequestBody HotelManagerDeleteReq hotelManagerDeleteReq) throws Exception {
        authService.verifyAdminToken();
        managerService.deleteHotelManager(hotelManagerDeleteReq);
    }
}
