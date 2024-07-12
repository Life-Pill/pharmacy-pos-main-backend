package com.lifepill.possystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test2")
public class TestControllerNew {

    @GetMapping("/test")
    public String test() {
        return "Test";
    }
}
