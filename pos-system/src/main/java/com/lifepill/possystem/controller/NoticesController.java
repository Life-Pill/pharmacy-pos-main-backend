package com.lifepill.possystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lifepill/v1/notices")
@CrossOrigin
public class NoticesController {

    @GetMapping("/notices")
    public String getNotices() {
        return "Notices";
    }
}
