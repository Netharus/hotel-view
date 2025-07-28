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
@Constraint(validatedBy = UniqueContactValidator.class)
@Documented
public @interface UniqueContact {
    String message() default "Contacts already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}