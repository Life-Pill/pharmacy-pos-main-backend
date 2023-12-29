package com.lifepill.possystem.dto.requestDTO.CashierUpdate;

import com.lifepill.possystem.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CashierUpdateAccountDetailsDTO {
    private int cashierId;
    private String cashierFirstName;
    private String cashierLastName;
    private Gender gender;
    private String cashierAddress;
    private Date dateOfBirth;

//    private String cashierAddressLine1;
//    private String cashierAddressLine2;
//    private String cashierAddressCity;
//    private String cashierAddressZipCode;

}
