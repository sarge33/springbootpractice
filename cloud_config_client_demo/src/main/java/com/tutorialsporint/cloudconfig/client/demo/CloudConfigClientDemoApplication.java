package com.tutorialsporint.cloudconfig.client.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RefreshScope
@RestController
public class CloudConfigClientDemoApplication {

    @Value("${my_app_name}")
    String my_app_name;

    @Value("${my_app_size}")
    String size;

    @Value("${welcome.message}")
    String welcomeText;

    public static void main(String[] args) {
        SpringApplication.run(CloudConfigClientDemoApplication.class, args);
    }

    @RequestMapping(value = "/welcome")
    public String welcomeText() {
        return welcomeText;
    }

    @RequestMapping(value = "/app_name")
    public String appName() {
        return my_app_name;
    }

    @RequestMapping(value = "/app_size")
    public String appSize() {
        return size;
    }
}