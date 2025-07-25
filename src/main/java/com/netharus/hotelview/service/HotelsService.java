package com.netharus.hotelview.service;

import com.netharus.hotelview.dto.response.FullHotelResponseDto;
import com.netharus.hotelview.dto.response.HotelResponseDto;

import java.util.List;

public interface HotelsService {
    List<HotelResponseDto> getHotelDtoList();

    FullHotelResponseDto findHotelById(Long id);
}
