package com.backend.api.controller.guest;

import com.backend.api.entity.TourEntity;
import com.backend.api.response.TourListRes;
import com.backend.api.service.TourService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/tours")
public class GuestTourController {
    private final TourService tourService;

    @Tag(name = "Upcoming Tours")
    @GetMapping("/upcoming")
    public List<TourListRes> submitQuestion() {
        return tourService.getUpcomingTours();
    }

    @Tag(name = "Get Tour By Place")
    @GetMapping("/place/{placesId}")
    public List<TourListRes> getToursByPlaceId(@PathVariable int placesId) throws Exception {
        return tourService.getToursByPlaceId(placesId);
    }

    @Tag(name = "Get Tour By Slug")
    @GetMapping("/{slug}")
    public TourEntity getTourBySlug(@PathVariable String slug) throws Exception {
        return tourService.getTourBySlug(slug);
    }
}
