package com.backend.api.controller.guest;

import com.backend.api.entity.HotelEntity;
import com.backend.api.entity.PlaceEntity;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.HotelService;
import com.backend.api.service.PaginationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/hotels")
public class GuestHotelController {
    private final HotelService hotelService;
    private final PaginationService paginationService;

    @Tag(name = "Get Hotel List")
    @GetMapping("")
    public PaginatedRes getHotels(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        List<HotelEntity> hotelEntities = hotelService.getHotels(name);
        Page<HotelEntity> pageInfo = (Page<HotelEntity>) hotelEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Random Hotels")
    @GetMapping("/random/{count}")
    public List<HotelEntity> getRandomHotels(@PathVariable int count) {
        return hotelService.getRandomHotels(count);
    }

    @Tag(name = "Get Hotel By ID")
    @GetMapping("/{id}")
    public HotelEntity getHotelById(@PathVariable int id) throws NotFoundException {
        return hotelService.getHotelById(id);
    }
}
