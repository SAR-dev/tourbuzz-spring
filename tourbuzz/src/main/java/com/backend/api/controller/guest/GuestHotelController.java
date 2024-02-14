package com.backend.api.controller.guest;

import com.backend.api.entity.HotelEntity;
import com.backend.api.service.HotelService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/hotels")
public class GuestHotelController {
    private final HotelService hotelService;

    @Tag(name = "Random Hotels")
    @GetMapping("/random/{count}")
    public List<HotelEntity> getRandomHotels(@PathVariable int count) {
        return hotelService.getRandomHotels(count);
    }
}
