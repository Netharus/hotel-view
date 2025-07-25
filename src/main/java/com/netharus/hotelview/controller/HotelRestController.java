package com.netharus.hotelview.controller;

import com.netharus.hotelview.dto.response.FullHotelResponseDto;
import com.netharus.hotelview.dto.response.HotelResponseDto;
import com.netharus.hotelview.service.HotelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/property-view/hotels")
@RequiredArgsConstructor
public class HotelRestController {

    private final HotelsService hotelsService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<HotelResponseDto> getHotels() {
        return hotelsService.getHotelDtoList();
    }

    @GetMapping("/{hotelId}")
    public FullHotelResponseDto getHotelById(@PathVariable Long hotelId) {
        return hotelsService.findHotelById(hotelId);
    }
}