package com.lifepill.possystem.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "cashier")
public class Cashier {
    @Id
    @Column(name = "cashier_id")
    private int cashierId;
    @Column(name = "cashier_name", nullable = false, length = 50)
    private String cashierName;
    /* @Column(name =  "cashier_password", nullable = false, length = 50)
     private String cashierPassword;*/
    @Column(name = "cashier_email", length = 50, unique = true)
    private String cashierEmail;
    @Column(name = "cashier_phone", length = 12)
    private String cashierPhone;
    @Column(name = "cashier_address", length = 100)
    private String cashierAddress;
    @Column(name = "cashier_sallary")
    private double cashierSalary;
    @Column(name = "cashier_nic", nullable = false, length = 12)
    private String cashierNic;
    @Column(name = "is_active", columnDefinition = "TINYINT default 0")
    private boolean isActiveStatus;

    @OneToMany(mappedBy = "cashiers")
    private Set<Order> orders;



    //    @Column(name = "is_active", columnDefinition = "BOOLEAN default false")
//    private boolean isActive;

}
