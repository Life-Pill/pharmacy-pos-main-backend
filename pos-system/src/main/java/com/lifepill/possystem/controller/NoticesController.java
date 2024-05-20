package com.lifepill.possystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing notices-related endpoints.
 */
@RestController
@RequestMapping("lifepill/v1/notices")
public class NoticesController {

    /**
     * Retrieves notices.
     *
     * @return A string indicating notices.
     */
    @GetMapping("/notices")
    public String getNotices() {
        return "Notices";
    }
}
