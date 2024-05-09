package com.lifepill.possystem.dto;


import com.lifepill.possystem.entity.EmployerBankDetails;
import com.lifepill.possystem.entity.enums.Gender;
import com.lifepill.possystem.entity.enums.Role;
import lombok.*;

import java.util.Date;

/**
 * The type Employer dto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployerWithBankDTO {
    private long employerId;
    private long branchId;
    private String employerNicName;
    private String employerFirstName;
    private String employerLastName;
    private String employerEmail;
    private String employerPhone;
    private String employerAddress;
    private double employerSalary;
    private String employerNic;
    private boolean isActiveStatus;
    private Gender gender;
    private Date dateOfBirth;
    private Role role;
    private int pin;
    private byte[] profileImage;

    private EmployerBankDetails employerBankDetails;
}