package com.netharus.hotelview.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueHotelNameValidator.class)
@Documented
public @interface UniqueHotelName {
    String message() default "Hotel already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
