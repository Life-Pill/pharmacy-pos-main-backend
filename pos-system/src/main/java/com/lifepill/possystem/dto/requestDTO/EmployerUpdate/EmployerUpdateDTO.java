package com.lifepill.possystem.dto.requestDTO.EmployerUpdate;

import com.lifepill.possystem.entity.enums.Role;
import lombok.*;

/**
 * The type Employer update dto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployerUpdateDTO {
    private long employerId;
//    private int branchId;
    private String employerNicName;
    private String employerEmail;
    private String employerNic;
    private String employerPhone;
    private double employerSalary;
    private Role role;

}