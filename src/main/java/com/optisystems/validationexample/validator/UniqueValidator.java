package com.optisystems.validationexample.validator;

import com.optisystems.validationexample.annotation.Unique;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator  implements ConstraintValidator<Unique, Long> {

    @Autowired
    EntityManager entityManager;

    private String propertyName;
    private Class<?> target;


    @Override
    public void initialize(Unique constraintAnnotation) {
        propertyName = constraintAnnotation.propertyName();
        target = constraintAnnotation.targetClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        boolean isValid = isPresent(value, propertyName, target);
        if(!isValid) {
            //do reject logic here
        }
        return isValid;
    }

    private boolean isPresent(Long value, String propertyName, Class<?> target) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(target);
        Root root = cq.from(target);

        cq.where(cb.equal(root.get(propertyName), value));

        return !entityManager.createQuery(cq).getResultList().isEmpty();
    }
}
