package com.lifepill.possystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "cashier")
public class Cashier {
    @Id
    @Column(name = "cashier_id")
    private int cashierId;
    @Column(name = "cashier_name", nullable = false, length = 50)
    private String cashierName;
    @Column(name =  "cashier_password", nullable = false, length = 50)
    private String cashierPassword;
    @Column(name = "cashier_email", length = 50, unique = true)
    private String cashierEmail;
    @Column(name = "cashier_phone", nullable = false, length = 50)
    private String cashierPhone;
    @Column(name = "cashier_address", length = 100)
    private String cashierAddress;
    @Column(name = "cashier_sallary")
    private double cashierSalary;
    @Column(name = "cashier_nic", nullable = false, length = 12)
    private String cashierNic;

    @Column(name = "is_active", columnDefinition = "TINYINT default 0")
    private boolean isActive;

//    @Column(name = "is_active", columnDefinition = "BOOLEAN default false")
//    private boolean isActive;


}
