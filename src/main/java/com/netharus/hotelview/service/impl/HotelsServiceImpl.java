package com.netharus.hotelview.service.impl;

import com.netharus.hotelview.dto.response.HotelDto;
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
    public List<HotelDto> getHotelDtoList() {
        return hotelMapper.fromHotelListToHotelDtoList(hotelsRepository.findAll());
    }
}
