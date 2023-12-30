package com.lifepill.possystem.entity;


import com.lifepill.possystem.entity.enums.Gender;
import com.lifepill.possystem.entity.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cashier")
@Builder
public class Cashier {
    @Id
    @Column(name = "cashier_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cashierId;
    @Column(name = "cashier_nic_name", length = 50)
    private String cashierNicName;
    @Column(name = "cashier_first_name", nullable = false, length = 50)
    private String cashierFirstName;
    @Column(name = "cashier_last_name", length = 50)
    private String cashierLastName;
    @Column(name = "cashier_password", nullable = false, length = 50)
    private String cashierPassword;
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
    @Column(name = "is_active", columnDefinition = "BOOLEAN default false")
    private boolean isActiveStatus;
    @Column(name = "pin",length = 4)
    private int pin;
//    @Column(name = "is_active", columnDefinition = "TINYINT default 0")
//    private boolean isActiveStatus;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10, nullable = false)
    private Gender gender;
    @Column(name = "cashier_date_of_birth", columnDefinition = "DATE")
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    @Column(name = "role",length = 15,nullable = false)
    private Role role;
    @OneToMany(mappedBy = "cashiers")
    private Set<Order> orders;

    @OneToOne(mappedBy = "cashier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CashierBankDetails cashierBankDetails;


    public Cashier(int cashierId, String cashierNicName, String cashierFirstName, String cashierLastName, String cashierPassword, String cashierEmail, String cashierPhone, String cashierAddress, double cashierSalary, String cashierNic, boolean isActiveStatus, int pin, Gender gender, Date dateOfBirth, Role role) {
        this.cashierId = cashierId;
        this.cashierNicName = cashierNicName;
        this.cashierFirstName = cashierFirstName;
        this.cashierLastName = cashierLastName;
        this.cashierPassword = cashierPassword;
        this.cashierEmail = cashierEmail;
        this.cashierPhone = cashierPhone;
        this.cashierAddress = cashierAddress;
        this.cashierSalary = cashierSalary;
        this.cashierNic = cashierNic;
        this.isActiveStatus = isActiveStatus;
        this.pin = pin;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }
}
