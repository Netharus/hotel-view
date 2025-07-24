package com.netharus.hotelview.service.impl;

import com.netharus.hotelview.repository.AmenityRepository;
import com.netharus.hotelview.service.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;
}
