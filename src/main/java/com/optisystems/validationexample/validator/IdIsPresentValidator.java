package com.optisystems.validationexample.validator;


import com.optisystems.validationexample.annotation.IdIsPresent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdIsPresentValidator implements ConstraintValidator<IdIsPresent, Long>{

    @Autowired
    private EntityManager entityManager;

    private Class<?> targetClass;

    @Override
    public void initialize(IdIsPresent idIsPresent) {
        targetClass = idIsPresent.targetClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        Object entity = entityManager.find(targetClass, value);
        boolean isValid = entity != null;
        if(!isValid) {
            //do reject logic
        }
        return isValid;
    }
}
