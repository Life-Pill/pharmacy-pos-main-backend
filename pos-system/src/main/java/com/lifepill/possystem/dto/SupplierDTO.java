package com.lifepill.possystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO {
    private long supplierId;
    private String supplierName;
    private String supplierAddress;
    private String supplierContact;
    private String supplierEmail;
    private String supplierDescription;
    private String supplierImage;


    private String supplierStatus;
    private String supplierRating;


    private String supplierBank;
    private String supplierAccountNumber;
    private String supplierBranch;
    private String supplierBranchCode;
    private String supplierBranchAddress;
    private String supplierBranchContact;
    private String supplierBranchEmail;
    private String supplierBranchStatus;
    private String supplierBranchRating;
    private String supplierBranchDiscount;
    private String supplierBranchPaymentMethod;

}
