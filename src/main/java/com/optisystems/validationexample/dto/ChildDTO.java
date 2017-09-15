package com.optisystems.validationexample.dto;

import com.optisystems.validationexample.annotation.IdIsPresent;
import com.optisystems.validationexample.annotation.Unique;
import com.optisystems.validationexample.entity.Child;
import com.optisystems.validationexample.entity.Parent;

public class ChildDTO {
    private long id;
    @IdIsPresent(targetClass = Parent.class)
    private long parentId;
    private String name;
    @Unique(propertyName = "unique", targetClass = Child.class)
    private long unique;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUnique() {
        return unique;
    }

    public void setUnique(long unique) {
        this.unique = unique;
    }
}
