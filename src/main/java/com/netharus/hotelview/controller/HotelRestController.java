package com.netharus.hotelview.controller;

import com.netharus.hotelview.dto.request.HotelCreateDto;
import com.netharus.hotelview.dto.response.FullHotelResponseDto;
import com.netharus.hotelview.dto.response.HotelResponseDto;
import com.netharus.hotelview.service.HotelsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @ResponseStatus(HttpStatus.OK)
    public FullHotelResponseDto getHotelById(@PathVariable Long hotelId) {
        return hotelsService.findHotelById(hotelId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public HotelResponseDto createHotel(@Valid @RequestBody HotelCreateDto hotelCreateDto) {
        return hotelsService.add(hotelCreateDto);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<HotelResponseDto> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) List<String> amenities
    ) {
        return hotelsService.searchHotels(name, brand, city, country, amenities);
    }
}