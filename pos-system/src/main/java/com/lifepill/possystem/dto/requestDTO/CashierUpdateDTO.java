package com.lifepill.possystem.dto.requestDTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CashierUpdateDTO {
    private int cashierId;
    private String cashierName;
    private String cashierEmail;
    private String cashierPhone;
    private double cashierSalary;
}