package com.paperfly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClassUtil03Application {

    public static void main(String[] args) {
        SpringApplication.run(ClassUtil03Application.class, args);
    }

}
