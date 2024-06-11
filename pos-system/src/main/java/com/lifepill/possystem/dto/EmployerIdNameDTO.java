package com.lifepill.possystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployerIdNameDTO {
    private long employerId;
    private String employerFirstName;
    private String employerLastName;
}