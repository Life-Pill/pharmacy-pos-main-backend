package com.lifepill.possystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lifepill/v1/contact")
@CrossOrigin
public class ContactUsController {

    @GetMapping("/contact-us")
    public String getContactUs() {
        return "Contact Us";
    }
}
