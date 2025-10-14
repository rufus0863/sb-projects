package com.demo;

import com.demo.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RestProjectDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestProjectDemoApplication.class, args);
    }
}
