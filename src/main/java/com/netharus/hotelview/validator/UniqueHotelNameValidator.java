package com.netharus.hotelview.validator;

import com.netharus.hotelview.repository.HotelsRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueHotelNameValidator implements ConstraintValidator<UniqueHotelName, String> {

    private final HotelsRepository hotelsRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !hotelsRepository.existsByNameIgnoreCase(name.trim());
    }

}
