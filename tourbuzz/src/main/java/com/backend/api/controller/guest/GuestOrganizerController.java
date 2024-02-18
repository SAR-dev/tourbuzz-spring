package com.backend.api.controller.guest;

import com.backend.api.entity.HotelEntity;
import com.backend.api.entity.OrganizerEntity;
import com.backend.api.service.HotelService;
import com.backend.api.service.OrganizerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/organizers")
public class GuestOrganizerController {
    private final OrganizerService organizerService;

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
