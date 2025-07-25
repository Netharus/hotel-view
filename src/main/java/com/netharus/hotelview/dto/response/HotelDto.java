package com.netharus.hotelview.dto.response;

public record HotelDto(
        Long id,
        String name,
        String description,
        String address,
        String phone
) {
}
