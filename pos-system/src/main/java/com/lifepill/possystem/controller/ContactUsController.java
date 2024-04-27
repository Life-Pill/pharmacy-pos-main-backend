package com.lifepill.possystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling contact us related operations.
 */
@RestController
@RequestMapping("lifepill/v1/contact")
@CrossOrigin
public class ContactUsController {

    /**
     * Retrieves the contact information.
     *
     * @return A string representing the contact information.
     */
    @GetMapping("/contact-us")
    public String getContactUs() {
        return "Contact Us";
    }
}
