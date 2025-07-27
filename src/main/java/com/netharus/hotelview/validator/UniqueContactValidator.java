package com.netharus.hotelview.validator;

import com.netharus.hotelview.dto.request.ContactCreateDto;
import com.netharus.hotelview.repository.ContactRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueContactValidator implements ConstraintValidator<UniqueContact, ContactCreateDto> {

    private final ContactRepository contactRepository;


    @Override
    public boolean isValid(ContactCreateDto contactCreateDto, ConstraintValidatorContext constraintValidatorContext) {
        if (contactCreateDto == null) {
            return true;
        }

        return !contactRepository.existsByEmailOrPhoneIgnoreCase(contactCreateDto.email().trim(),
                contactCreateDto.phone().trim());
    }
}
