package com.fabio.rinha2.web;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class HelloWorldController {

    @PostConstruct
    public void init() {
        // Log statement indicating this method was called
        System.out.println("Application Started Successfully");
    }

    @GetMapping
    public String hello() {
        return "Hello World";
    }

}