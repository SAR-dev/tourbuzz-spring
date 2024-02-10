package com.backend.api.controller.admin;

import com.backend.api.entity.PlaceEntity;
import com.backend.api.request.admin.PlaceCreateReq;
import com.backend.api.request.admin.PlaceUpdateReq;
import com.backend.api.response.PaginatedRes;
import com.backend.api.response.PlaceTypeRes;
import com.backend.api.service.AuthService;
import com.backend.api.service.PaginationService;
import com.backend.api.service.admin.PlaceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/places")
public class AdminPlaceController {
    private final AuthService authService;
    private final PaginationService paginationService;
    private final PlaceService placeService;

    @Tag(name = "Create Place")
    @PostMapping("")
    public void createPlace(@Valid @RequestBody PlaceCreateReq placeCreateReq) throws Exception {
        authService.verifyAdminToken();
        placeService.createPlace(placeCreateReq);
    }

    @Tag(name = "Get Place List")
    @GetMapping("")
    public PaginatedRes getPlaces(
            @RequestParam(name = "name", defaultValue = "") String name,
            @RequestParam(name = "page_no", defaultValue = "1") int pageNo,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) throws Exception {
        authService.verifyAdminToken();
        PageHelper.startPage(pageNo, pageSize);
        List<PlaceEntity> placeEntities = placeService.getPlaces(name);
        Page<PlaceEntity> pageInfo = (Page<PlaceEntity>) placeEntities;
        return paginationService.getPaginatedData(pageInfo, pageNo, pageSize);
    }

    @Tag(name = "Get Place Types")
    @GetMapping("/types")
    public List<PlaceTypeRes> getPlaceTypes() throws Exception {
        authService.verifyAdminToken();
        return placeService.getPlaceTypes();
    }

    @Tag(name = "Update Place")
    @PutMapping("")
    public void updatePlace(@Valid @RequestBody PlaceUpdateReq placeUpdateReq) throws Exception {
        authService.verifyAdminToken();
        placeService.updatePlace(placeUpdateReq);
    }

    @Tag(name = "Get Place By ID")
    @GetMapping("/{id}")
    public PlaceEntity getPlaceById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        return placeService.getPlaceById(id);
    }

    @Tag(name = "Delete Place By ID")
    @DeleteMapping("/{id}")
    public void deletePlaceById(@PathVariable int id) throws Exception {
        authService.verifyAdminToken();
        placeService.deletePlaceById(id);
    }
}
