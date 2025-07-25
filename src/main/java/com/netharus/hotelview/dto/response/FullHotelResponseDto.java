package com.netharus.hotelview.dto.response;

import java.util.List;

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
