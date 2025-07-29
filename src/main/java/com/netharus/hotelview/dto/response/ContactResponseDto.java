package com.netharus.hotelview.dto.response;

import lombok.Builder;

@Builder
public record ContactResponseDto(
        String phone,
        String email
) {
}
