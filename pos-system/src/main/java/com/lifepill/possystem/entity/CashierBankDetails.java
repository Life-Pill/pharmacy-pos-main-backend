package com.lifepill.possystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cashierbankdetails")
public class CashierBankDetails
{
    @Id
    @Column(name = "cashier_id")
    private long cashierId;
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
    @OneToOne
    @JoinColumn(name = "cashier_id")
    private Cashier cashier;

}
