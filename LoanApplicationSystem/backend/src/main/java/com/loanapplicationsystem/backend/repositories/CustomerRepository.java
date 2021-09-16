package com.loanapplicationsystem.backend.repositories;

import com.loanapplicationsystem.backend.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
