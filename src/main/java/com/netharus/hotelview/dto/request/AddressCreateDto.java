package com.netharus.hotelview.dto.request;

import com.netharus.hotelview.validator.UniqueAddress;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@UniqueAddress
public record AddressCreateDto(
        @NotNull
        @Positive
        Integer houseNumber,
        @NotBlank
        String street,
        @NotBlank
        String city,
        @NotBlank
        String country,
        @NotBlank
        String postCode
) {
}
