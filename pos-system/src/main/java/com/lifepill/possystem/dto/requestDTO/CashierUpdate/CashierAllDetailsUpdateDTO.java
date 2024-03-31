package com.lifepill.possystem.dto.requestDTO.CashierUpdate;

import com.lifepill.possystem.entity.enums.Gender;
import com.lifepill.possystem.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CashierAllDetailsUpdateDTO {
 //   private long cashierId;
    private int branchId;
    private String cashierNicName;
    private String cashierFirstName;
    private String cashierLastName;
    private String cashierPassword;
    private String cashierEmail;
    private String cashierPhone;
    private String cashierAddress;
    private double cashierSalary;
    private String cashierNic;
    private boolean isActiveStatus;
    private Gender gender;
    private Date dateOfBirth;
    private Role role;
    private int pin;

}