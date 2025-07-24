package com.netharus.hotelview.service.impl;

import com.netharus.hotelview.repository.AddressRepository;
import com.netharus.hotelview.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
}
