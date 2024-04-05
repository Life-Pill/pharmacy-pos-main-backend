package com.lifepill.possystem.dto.requestDTO.EmployerUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployerPasswordResetDTO {
    private long employerId;
    private String employerPassword;
}
