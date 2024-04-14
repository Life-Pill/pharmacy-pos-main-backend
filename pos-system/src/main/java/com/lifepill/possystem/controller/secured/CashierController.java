package com.lifepill.possystem.controller.secured;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lifepill/v1/cashierNew")
public class CashierController {
    @GetMapping
    public String getCashier() {
        return "Secured Endpoint :: GET - Cashier controller";
    }

    @PostMapping
    public String post() {
        return "POST:: Cashier controller";
    }
}
