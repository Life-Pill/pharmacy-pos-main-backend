package com.lifepill.possystem.dto.requestDTO.EmployerUpdate;

import com.lifepill.possystem.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployerUpdateAccountDetailsDTO {
    private long employerId;
    private String employerFirstName;
    private String employerLastName;
    private Gender gender;
    private String employerAddress;
    private Date dateOfBirth;

//    private String cashierAddressLine1;
//    private String cashierAddressLine2;
//    private String cashierAddressCity;
//    private String cashierAddressZipCode;

}
