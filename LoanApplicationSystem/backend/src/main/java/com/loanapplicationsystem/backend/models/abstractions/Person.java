package com.loanapplicationsystem.backend.models.abstractions;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class Person extends AbstractBaseEntity{
    private String firstName;
    private String LastName;
    private String phoneNumber;
}
