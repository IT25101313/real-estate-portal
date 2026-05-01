package com.realestate.realestateportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RealEstatePortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(RealEstatePortalApplication.class, args);
    }
}