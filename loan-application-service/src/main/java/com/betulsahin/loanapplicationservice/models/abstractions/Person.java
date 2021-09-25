package com.betulsahin.loanapplicationservice.models.abstractions;

import com.betulsahin.loanapplicationservice.models.abstractions.AbstractBaseEntity;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class Person extends AbstractBaseEntity {
    private String firstName;
    private String LastName;
    private String phoneNumber;
}
