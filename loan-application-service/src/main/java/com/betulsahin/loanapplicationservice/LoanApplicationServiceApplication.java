package com.betulsahin.loanapplicationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
public class LoanApplicationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanApplicationServiceApplication.class, args);
    }
}
