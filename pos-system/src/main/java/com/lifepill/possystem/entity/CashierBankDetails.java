package com.lifepill.possystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cashierbankdetails")
public class CashierBankDetails
{
    @Id
    @Column(name = "cashier_id")
    private int cashierId;
    @Column(name = "bank_name",nullable = true)
    private String bankName;
    @Column(name = "bank_account_number",nullable = true)
    private String bankAccountNumber;
    @Column(name = "cashier_description",nullable = true)
    private String cashierDescription;
}
