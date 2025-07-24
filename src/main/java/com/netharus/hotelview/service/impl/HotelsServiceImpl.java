package com.netharus.hotelview.service.impl;

import com.netharus.hotelview.repository.HotelsRepository;
import com.netharus.hotelview.service.HotelsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelsServiceImpl implements HotelsService {

    private final HotelsRepository hotelsRepository;
}
