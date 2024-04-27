package com.lifepill.possystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Supplier dto.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO {
    private long supplierId;
    private long companyId;
    private String supplierName;
    private String supplierAddress;
    private String supplierPhone;
    private String supplierEmail;
    private String supplierDescription;
    private String supplierImage;
    private String supplierRating;

}
