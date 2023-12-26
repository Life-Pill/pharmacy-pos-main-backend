package com.lifepill.possystem.dto;

import com.lifepill.possystem.entity.Order;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CashierDTO {
    private int cashierId;
    private String cashierName;
   // private String cashierPassword;
    private String cashierEmail;
    private String cashierPhone;
    private String cashierAddress;
    private double cashierSalary;
    private String cashierNic;
    private boolean isActiveStatus;
    private Order order;
}