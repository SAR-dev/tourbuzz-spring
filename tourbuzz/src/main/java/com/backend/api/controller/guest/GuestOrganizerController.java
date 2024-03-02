package com.backend.api.controller.guest;

import com.backend.api.entity.HotelEntity;
import com.backend.api.entity.OrganizerEntity;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.HotelService;
import com.backend.api.service.OrganizerService;
import com.backend.api.service.PaginationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/organizers")
public class GuestOrganizerController {
    private final OrganizerService organizerService;
    private final PaginationService paginationService;

    @Tag(name = "Get Organizer List")
    @GetMapping("")
    public PaginatedRes getOrganizers(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        List<OrganizerEntity> organizerEntities = organizerService.getOrganizers(name, email);
        Page<OrganizerEntity> pageInfo = (Page<OrganizerEntity>) organizerEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Random Organizers")
    @GetMapping("/random/{count}")
    public List<OrganizerEntity> getRandomOrganizers(@PathVariable int count) {
        return organizerService.getRandomOrganizers(count);
    }

    @Tag(name = "Get Organizer By Username")
    @GetMapping("/{username}")
    public OrganizerEntity getOrganizerById(@PathVariable String username){
        return organizerService.getOrganizerByUsername(username);
    }
}
