package com.betulsahin.loanapplicationservice.utils;

import com.betulsahin.loanapplicationservice.models.Loan;
import com.betulsahin.loanapplicationservice.models.Customer;
import com.betulsahin.loanapplicationservice.models.enums.CreditResult;
import com.betulsahin.loanapplicationservice.repositories.CustomerRepository;
import com.betulsahin.loanapplicationservice.repositories.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InitializerRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(InitializerRunner.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public void run(String... args) throws Exception {
        // saved customers to db
        Customer customer1 = new Customer();
        customer1.setIdentificationNumber("67098197133");
        customer1.setFirstName("Gibbie");
        customer1.setLastName("Wyndham");
        customer1.setMonthlyIncome(159000.0);
        customer1.setPhoneNumber("05231231212");
        customer1 = customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setIdentificationNumber("80376873280");
        customer2.setFirstName("Charita");
        customer2.setLastName("Gurko");
        customer2.setMonthlyIncome(2359000.0);
        customer2.setPhoneNumber("05231231212");
        customer2 = customerRepository.save(customer2);

        // saved loans to db
        Loan loan1 = new Loan();
        loan1.setCreditAmount(10.000);
        loan1.setCreditResult(CreditResult.APPROVAL);
        loan1.setCustomer(customer1);
        loanRepository.save(loan1);

        Loan loan2 = new Loan();
        loan2.setCreditAmount(0);
        loan2.setCreditResult(CreditResult.REJECTION);
        loan2.setCustomer(customer1);
        loanRepository.save(loan2);

        Loan loan3 = new Loan();
        loan3.setCreditAmount(20.000);
        loan3.setCreditResult(CreditResult.APPROVAL);
        loan3.setCustomer(customer1);
        loanRepository.save(loan3);

        Loan loan4 = new Loan();
        loan4.setCreditAmount(10.000);
        loan4.setCreditResult(CreditResult.APPROVAL);
        loan4.setCustomer(customer2);
        loanRepository.save(loan4);

       // List<Loan> loanList = Arrays.asList(loan1, loan2, loan3, loan4);
       // loanRepository.saveAll(loanList);
    }
}

