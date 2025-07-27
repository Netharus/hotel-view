package com.netharus.hotelview.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueAddressValidator.class)
@Documented
public @interface UniqueAddress {
    String message() default "Address already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
