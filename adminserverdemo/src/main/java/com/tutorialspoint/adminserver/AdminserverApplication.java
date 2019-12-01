package com.tutorialspoint.adminserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
//import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@SpringBootApplication
public class AdminserverApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminserverApplication.class, args);
    }
}