package com.netharus.hotelview.dto.response;

import lombok.Builder;

@Builder
public record ArrivalTimeResponseDto(
        String checkIn,
        String checkOut
) {
}
