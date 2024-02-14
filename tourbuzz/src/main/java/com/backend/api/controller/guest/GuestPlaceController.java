package com.backend.api.controller.guest;

import com.backend.api.entity.PlaceEntity;
import com.backend.api.service.PlaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/places")
public class GuestPlaceController {
    private final PlaceService placeService;

    @Tag(name = "Popular Places")
    @GetMapping("/popular/{count}")
    public List<PlaceEntity> getPopularPlaces(@PathVariable int count) {
        return placeService.getPopularPlaces(count);
    }
}
