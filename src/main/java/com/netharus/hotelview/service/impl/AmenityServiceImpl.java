package com.netharus.hotelview.service.impl;

import com.netharus.hotelview.domain.Amenity;
import com.netharus.hotelview.repository.AmenityRepository;
import com.netharus.hotelview.service.AmenityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {

    private final AmenityRepository amenityRepository;

    @Override
    @Transactional
    public void saveMissingAmenities(List<String> amenities) {
        List<Amenity> existing = amenityRepository.findByNameInIgnoreCase(amenities);
        Set<String> existingNames = existing.stream()
                .map(Amenity::getName)
                .collect(Collectors.toSet());

        List<Amenity> newOnes = amenities.stream()
                .filter(name -> !existingNames.contains(name))
                .map(name -> {
                    Amenity a = new Amenity();
                    a.setName(name);
                    return a;
                })
                .toList();

        amenityRepository.saveAll(newOnes);
    }

    @Override
    @Transactional
    public List<Amenity> getAmenitiesByName(List<String> amenityName) {
        return amenityRepository.findByNameInIgnoreCase(amenityName);
    }

}
