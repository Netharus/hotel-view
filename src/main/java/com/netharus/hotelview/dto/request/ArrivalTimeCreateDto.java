package com.netharus.hotelview.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record ArrivalTimeCreateDto(
        @NotBlank
        String checkIn,
        @Nullable
        String checkOut
) {
}
