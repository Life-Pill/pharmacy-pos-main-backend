package com.lifepill.possystem.dto;

import com.lifepill.possystem.entity.enums.Role;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateManagerDTO {
    private long branchId;
    private String employerFirstName;
    private String employerLastName;
    private String employerEmail;
    private String employerPassword;
    private Role role;
    private int pin;
}
