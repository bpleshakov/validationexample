package com.optisystems.validationexample.repository;

import com.optisystems.validationexample.entity.Child;
import org.springframework.data.repository.CrudRepository;

public interface ChildRepository extends CrudRepository<Child, Long> {
}
