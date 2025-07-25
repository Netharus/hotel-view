package com.netharus.hotelview.service.impl;

import com.netharus.hotelview.dto.response.FullHotelResponseDto;
import com.netharus.hotelview.dto.response.HotelResponseDto;
import com.netharus.hotelview.exceptions.ErrorMessages;
import com.netharus.hotelview.exceptions.HotelNotFoundException;
import com.netharus.hotelview.mapper.HotelMapper;
import com.netharus.hotelview.repository.HotelsRepository;
import com.netharus.hotelview.service.HotelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelsServiceImpl implements HotelsService {

    private final HotelsRepository hotelsRepository;

    private final HotelMapper hotelMapper;

    @Override
    public List<HotelResponseDto> getHotelDtoList() {
        return hotelMapper.fromHotelListToHotelDtoList(hotelsRepository.findAll());
    }

    @Override
    public FullHotelResponseDto findHotelById(Long id) {
        return hotelsRepository.findById(id).map(hotelMapper::fromHotelToFullHotelDto)
                .orElseThrow(() -> new HotelNotFoundException(String.format(ErrorMessages.HOTEL_NOT_FOUND, id)));
    }
}
