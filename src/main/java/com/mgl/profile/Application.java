package com.mgl.profile;

import com.mgl.profile.config.SwaggerConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class Application {

    @Autowired
    private SwaggerConfigProperties swaggerConfigProperties;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("Application started with arguments {}",args);
    }

    @PostConstruct
    private void init() {
        log.info("Application is up and running with following properties");
        log.info("Swagger configuration {}", swaggerConfigProperties.toString());
    }
}
