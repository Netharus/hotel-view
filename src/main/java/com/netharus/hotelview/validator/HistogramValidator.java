package com.netharus.hotelview.validator;

import com.netharus.hotelview.exceptions.ErrorMessages;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HistogramValidator implements ConstraintValidator<HistogramValid, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }

        boolean isValid = AllowedParametersHistogram.getParameterNames().contains(s.toLowerCase());

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    ErrorMessages.ILLEGAL_HISTOGRAM_PARAM_VALIDATOR.formatted(
                            s,
                            String.join(", ", AllowedParametersHistogram.getParameterNames())
                    )
            ).addConstraintViolation();
        }

        return isValid;
    }
}
