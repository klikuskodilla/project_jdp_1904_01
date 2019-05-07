package com.kodilla.ecommercee;

import javax.persistence.*;

@Entity
public class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String value;

    public GenericEntity() {

    }

    public GenericEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setValue(String value) {
        this.value = value;
    }
}
