package com.netharus.hotelview.dto.response;

import lombok.Builder;

@Builder
public record HotelResponseDto(
        Long id,
        String name,
        String description,
        String address,
        String phone
) {
}
