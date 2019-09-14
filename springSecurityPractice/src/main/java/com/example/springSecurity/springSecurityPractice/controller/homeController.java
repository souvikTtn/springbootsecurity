package com.example.springSecurity.springSecurityPractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {

    @GetMapping("/welcome")
    public String getHomePage(){
        return "<h1>welcome spring security</h1>";
    }
}
