package com.portfolio.carfactoryapi;

import com.portfolio.carfactoryapi.configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class CarFactoryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarFactoryApiApplication.class, args);
    }

}
