package com.netharus.hotelview.dto.response;

import lombok.Builder;

@Builder
public record AddressResponseDto(
        Integer houseNumber,
        String street,
        String city,
        String country,
        String postCode
) {
}
