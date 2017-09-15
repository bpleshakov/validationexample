package com.optisystems.validationexample.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "id")
    private long id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "parent")
    private List<Child> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
