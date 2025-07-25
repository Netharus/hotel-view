package com.netharus.hotelview.service;

import com.netharus.hotelview.dto.response.HotelDto;

import java.util.List;

public interface HotelsService {
    List<HotelDto> getHotelDtoList();
}
