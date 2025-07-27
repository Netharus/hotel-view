package com.netharus.hotelview.service.impl;

import com.netharus.hotelview.repository.ArrivalTimeRepository;
import com.netharus.hotelview.service.ArrivalTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArrivalTimeServiceImpl implements ArrivalTimeService {

    private final ArrivalTimeRepository arrivalTimeRepository;
}
