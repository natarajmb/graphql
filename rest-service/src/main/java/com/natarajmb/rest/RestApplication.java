package com.natarajmb.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * REST service application to serve following domains
 * - product
 * - price
 * - stock
 */
@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestApplication.class, args);

    }
    
}
