package com.lifepill.possystem.dto;


import com.lifepill.possystem.entity.enums.Gender;
import com.lifepill.possystem.entity.enums.Role;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployerNewDTO {
        private String employerNicName;
        private String employerFirstName;
        private String employerLastName;
        private String employerPassword;
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

}
