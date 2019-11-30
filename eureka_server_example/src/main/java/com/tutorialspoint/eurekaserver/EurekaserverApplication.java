package com.tutorialspoint.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@EnableEurekaServer
// note default application properties file is: resources/application.properties
//@PropertySource("classpath:applications.properties")
@PropertySource("classpath:applications.properties")
public class EurekaserverApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaserverApplication.class, args);
    }
}