package com.lifepill.possystem.controller;

import com.lifepill.possystem.dto.EmployerDTO;
import com.lifepill.possystem.entity.enums.Role;
import com.lifepill.possystem.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lifepill/v1/owner")
public class OwnerController {

    @Autowired
    private EmployerService employerService;

    @GetMapping("/byRole/{role}")
    public ResponseEntity<List<EmployerDTO>> getAllEmployerByRole(@PathVariable Role role) {
        List<EmployerDTO> cashiers = employerService.getAllEmployerByRole(role);
        return new ResponseEntity<>(cashiers, HttpStatus.OK);
    }
}
