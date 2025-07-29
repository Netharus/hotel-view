package com.netharus.hotelview.validator;

import com.netharus.hotelview.dto.request.AddressCreateDto;
import com.netharus.hotelview.repository.AddressRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueAddressValidator implements ConstraintValidator<UniqueAddress, AddressCreateDto> {

    private final AddressRepository addressRepository;


    @Override
    public boolean isValid(AddressCreateDto dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true;
        }

        return !addressRepository.existsAddressByHouseNumberAndStreetAndCityAndCountryAndPostCodeIgnoreCase(
                dto.houseNumber(),
                dto.street().trim(),
                dto.city().trim(),
                dto.country().trim(),
                dto.postCode().trim());
    }


}
