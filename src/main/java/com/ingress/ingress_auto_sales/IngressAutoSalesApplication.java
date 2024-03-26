package com.ingress.ingress_auto_sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// Add the mapper package for component scanning

@SpringBootApplication
public class IngressAutoSalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(IngressAutoSalesApplication.class, args);
    }

}
