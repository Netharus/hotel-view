package com.netharus.hotelview.dto.request;

import com.netharus.hotelview.validator.UniqueHotelName;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;


public record HotelCreateDto(
        @NotBlank
        @UniqueHotelName
        String name,
        @Nullable
        String description,
        @NotBlank
        String brand,
        @Valid
        AddressCreateDto address,
        @Valid
        ContactCreateDto contacts,
        @Valid
        ArrivalTimeCreateDto arrivalTime
) {
}
