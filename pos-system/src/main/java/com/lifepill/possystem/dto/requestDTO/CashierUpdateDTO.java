package com.lifepill.possystem.dto.requestDTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CashierUpdateDTO {
    private int cashierId;
    private String cashierName;
    private String cashierEmail;
    private String cashierPhone;
    private double cashierSalary;
}
