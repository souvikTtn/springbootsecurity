package com.example.springSecurity.springSecurityPractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {
    @GetMapping("/welcome")
    public String getHomePage(){
        return "<h1>welcome spring security accessed by all</h1>";
    }
    @GetMapping("/users")
    public String getUserPage(){
        return "<h1> this is a user page only accessible by admin and user</h1>";
    }
    @GetMapping("/admin")
    public String getAdminPage(){
        return "<h1> this is a Admin page only accessible by admin</h1>";
    }
}
