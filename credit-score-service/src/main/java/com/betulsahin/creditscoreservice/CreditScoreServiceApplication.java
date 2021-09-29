package com.betulsahin.creditscoreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CreditScoreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditScoreServiceApplication.class, args);
    }

}
