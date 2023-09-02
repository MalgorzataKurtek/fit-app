package com.example.fitApp.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class Controller {
    @GetMapping("/index")
    public String home() {
        return "index";
    }
}
