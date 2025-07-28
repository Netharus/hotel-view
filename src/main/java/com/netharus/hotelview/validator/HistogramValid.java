package com.netharus.hotelview.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = HistogramValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HistogramValid {
    String message() default "Invalid histogram parameter";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
