package com.netharus.hotelview.service;

import com.netharus.hotelview.domain.Amenity;

import java.util.List;

public interface AmenityService {

    void saveMissingAmenities(List<String> amenities);

    List<Amenity> getAmenitiesByName(List<String> amenityName);
}
