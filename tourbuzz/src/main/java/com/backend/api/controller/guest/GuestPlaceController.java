package com.backend.api.controller.guest;

import com.backend.api.entity.PlaceEntity;
import com.backend.api.filter.GuestPlaceFilter;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.PaginationService;
import com.backend.api.service.PlaceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/places")
public class GuestPlaceController {
    private final PlaceService placeService;
    private final PaginationService paginationService;

    @Tag(name = "Get Place List")
    @GetMapping("")
    public PaginatedRes getPlaces(@Valid GuestPlaceFilter filter) throws Exception {
        PageHelper.startPage(filter.getPageNo(), filter.getPageNo());
        List<PlaceEntity> placeEntities = placeService.getPlaces(filter.getName());
        Page<PlaceEntity> pageInfo = (Page<PlaceEntity>) placeEntities;
        return paginationService.getPaginatedData(pageInfo, filter.getPageNo(), filter.getPageNo());
    }

    @Tag(name = "Popular Places")
    @GetMapping("/popular/{count}")
    public List<PlaceEntity> getPopularPlaces(@PathVariable int count) {
        return placeService.getPopularPlaces(count);
    }

    @Tag(name = "Get Place By ID")
    @GetMapping("/{id}")
    public PlaceEntity getPlaceById(@PathVariable int id) throws NotFoundException {
        return placeService.getPlaceById(id);
    }
}
