package com.backend.api.controller.common;

import com.backend.api.response.TourListRes;
import com.backend.api.service.TourService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/common/tours")
public class CommonTourController {
    private final TourService tourService;

    @Tag(name = "Upcoming Tours")
    @GetMapping("/upcoming")
    public List<TourListRes> submitQuestion() {
        return tourService.getUpcomingTours();
    }
}
