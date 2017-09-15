package com.optisystems.validationexample.repository;

import com.optisystems.validationexample.entity.Parent;
import org.springframework.data.repository.CrudRepository;

public interface ParentRepository extends CrudRepository<Parent, Long> {
}
