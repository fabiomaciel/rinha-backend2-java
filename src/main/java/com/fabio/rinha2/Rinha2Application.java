package com.fabio.rinha2;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Rinha2Application {

    public static void main(String[] args) {
        SpringApplication.run(Rinha2Application.class, args);
    }

}

