package com.kodilla.ecommercee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Generic_Entity")
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

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "Id" ,unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "Value")
    public String getValue() {
        return value;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setValue(String value) {
        this.value = value;
    }
}
