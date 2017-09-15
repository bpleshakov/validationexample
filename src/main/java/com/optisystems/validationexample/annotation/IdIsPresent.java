package com.optisystems.validationexample.annotation;

import com.optisystems.validationexample.validator.IdIsPresentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { IdIsPresentValidator.class })
public @interface IdIsPresent {
    String message() default "Data is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> targetClass();
}
