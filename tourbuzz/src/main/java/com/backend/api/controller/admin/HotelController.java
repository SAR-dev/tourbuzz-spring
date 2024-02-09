package com.backend.api.controller.admin;

import com.backend.api.entity.HotelEntity;
import com.backend.api.entity.WriterEntity;
import com.backend.api.request.admin.HotelCreateReq;
import com.backend.api.request.admin.HotelUpdateReq;
import com.backend.api.request.admin.WriterCreateReq;
import com.backend.api.request.admin.WriterUpdateReq;
import com.backend.api.response.PaginatedRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.admin.HotelService;
import com.backend.api.service.admin.WriterService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/hotels")
public class HotelController {
    private final AuthService authService;
    private final PaginationService paginationService;
    private final HotelService hotelService;

    @Tag(name = "Create Hotel")
    @PostMapping("")
    public void createHotel(@Valid @RequestBody HotelCreateReq hotelCreateReq) throws Exception {
        authService.verifyAdminToken();
        hotelService.createHotel(hotelCreateReq);
    }

    @Tag(name = "Get Hotel List")
    @GetMapping("")
    public PaginatedRes getHotels(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<HotelEntity> hotelEntities = hotelService.getHotels(name);
        Page<HotelEntity> pageInfo = (Page<HotelEntity>) hotelEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Update Hotel")
    @PutMapping("")
    public void updateHotel(@Valid @RequestBody HotelUpdateReq hotelUpdateReq) throws Exception {
        authService.verifyAdminToken();
        hotelService.updateHotel(hotelUpdateReq);
    }

    @Tag(name = "Get Hotel By ID")
    @GetMapping("/{id}")
    public HotelEntity getHotelById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return hotelService.getHotelById(id);
    }

    @Tag(name = "Delete Hotel By ID")
    @DeleteMapping("/{id}")
    public void deleteHotelById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        hotelService.deleteHotelById(id);
    }
}
