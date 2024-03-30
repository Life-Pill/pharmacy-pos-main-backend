package com.lifepill.possystem.dto.requestDTO.CashierUpdate;

import com.lifepill.possystem.entity.Order;
import com.lifepill.possystem.entity.enums.Gender;
import com.lifepill.possystem.entity.enums.Role;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CashierUpdateDTO {
    private long cashierId;
    private String cashierNicName;
    private String cashierEmail;
    private String cashierNic;
    private String cashierPhone;
    private double cashierSalary;
    private Role role;

}