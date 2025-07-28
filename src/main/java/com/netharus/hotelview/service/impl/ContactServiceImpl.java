package com.netharus.hotelview.service.impl;

import com.netharus.hotelview.repository.ContactRepository;
import com.netharus.hotelview.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
}
