package com.backend.api.controller.guest;

import com.backend.api.entity.TourEntity;
import com.backend.api.response.PaginatedRes;
import com.backend.api.response.TourListRes;
import com.backend.api.service.PaginationService;
import com.backend.api.service.TourService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
    private final PaginationService paginationService;

    @Tag(name = "Get Tour List")
    @GetMapping("")
    public PaginatedRes getTours(
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        List<TourListRes> tourEntities = tourService.getTours();
        Page<TourListRes> pageInfo = (Page<TourListRes>) tourEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Upcoming Tours")
    @GetMapping("/upcoming")
    public List<TourListRes> submitQuestion() {
        return tourService.getUpcomingTours();
    }

    @Tag(name = "Get Tour By Place")
    @GetMapping("/place/{placesId}")
    public List<TourListRes> getToursByPlaceId(@PathVariable int placesId) {
        return tourService.getToursByPlaceId(placesId);
    }

    @Tag(name = "Get Tour By Hotel")
    @GetMapping("/hotel/{hotelsId}")
    public List<TourListRes> getToursByHotelId(@PathVariable int hotelsId) {
        return tourService.getToursByHotelId(hotelsId);
    }

    @Tag(name = "Get Tour By Organizer")
    @GetMapping("/organizer/{organizersId}")
    public List<TourListRes> getToursByOrganizerId(@PathVariable int organizersId) {
        return tourService.getToursByOrganizerId(organizersId);
    }

    @Tag(name = "Get Tour By Slug")
    @GetMapping("/{slug}")
    public TourEntity getTourBySlug(@PathVariable String slug) throws Exception {
        return tourService.getTourBySlug(slug);
    }
}
