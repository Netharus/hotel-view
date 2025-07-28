package com.netharus.hotelview.dto.request;

import com.netharus.hotelview.validator.UniqueContact;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@UniqueContact
public record ContactCreateDto(
        @NotBlank
        String phone,
        @NotBlank
        @Email
        String email
) {
}
