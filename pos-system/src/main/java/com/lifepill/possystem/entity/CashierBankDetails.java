package com.lifepill.possystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cashier_bankdetails")
public class CashierBankDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cashier_bank_details_id")
    private long cashierBankDetailsId;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_branch_name",nullable = true)
    private String bankBranchName;
    @Column(name = "bank_account_number")
    private String bankAccountNumber;
    @Column(name = "cashier_description")
    private String cashierDescription;
    @Column(name = "cashier_monthly_payment")
    private double monthlyPayment;
    @Column(name = "payment_status",nullable = true)
    private Boolean monthlyPaymentStatus;
    @Column(name = "cashier_id")
    private long cashierId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cashierBankDetails")
    private Set<Cashier> cashiers;


}
