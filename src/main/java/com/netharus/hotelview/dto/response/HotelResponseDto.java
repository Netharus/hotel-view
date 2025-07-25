package com.netharus.hotelview.dto.response;

public record HotelResponseDto(
        Long id,
        String name,
        String description,
        String address,
        String phone
) {
}
