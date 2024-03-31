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
    private long cashierId;
    @Column(name = "cashier_nic_name", length = 50)
    private String cashierNicName;
    @Column(name = "cashier_first_name", nullable = false, length = 50)
    private String cashierFirstName;
    @Column(name = "cashier_last_name", length = 50)
    private String cashierLastName;
    @Lob
    @Column(name = "profile_image",nullable = true)
    private byte[] profileImage;
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
    @Column(name = "is_active", columnDefinition = "BOOLEAN default false") //columnDefinition = "TINYINT default 0
    private boolean isActiveStatus;
    @Column(name = "pin",length = 4)
    private int pin;
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

    @ManyToOne
    @JoinColumn(name = "cashier_bank_details_id", nullable = true)
    private CashierBankDetails cashierBankDetails;

    @ManyToOne
    @JoinColumn(name = "brach_id", nullable = false)
    private Branch branch;

}
