package com.lifepill.possystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * The type Employer bank details.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employer_bankdetails")
public class EmployerBankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_bank_details_id")
    private long employerBankDetailsId;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_branch_name",nullable = true)
    private String bankBranchName;
    @Column(name = "bank_account_number")
    private String bankAccountNumber;
    @Column(name = "employer_description")
    private String employerDescription;
    @Column(name = "cashier_monthly_payment")
    private double monthlyPayment;
    @Column(name = "payment_status",nullable = true)
    private Boolean monthlyPaymentStatus;
    @Column(name = "employeer_id")
    private long employerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employerBankDetails")
    private Set<Employer> employers;
}
