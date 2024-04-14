package com.lifepill.possystem.controller.secured;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lifepill/v1")
public class testController {

    @GetMapping("/test")
    public String test() {
        return "Test Controller";
    }
}
