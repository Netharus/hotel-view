package com.netharus.hotelview.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record FullHotelResponseDto(
        Long id,
        String name,
        String description,
        String brand,
        AddressResponseDto address,
        ContactResponseDto contacts,
        ArrivalTimeResponseDto arrivalTime,
        List<String> amenities
) {
}
