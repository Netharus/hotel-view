package com.netharus.hotelview.dto.response;

public record AddressResponseDto(
        Integer houseNumber,
        String street,
        String city,
        String country,
        String postCode
) {
}
