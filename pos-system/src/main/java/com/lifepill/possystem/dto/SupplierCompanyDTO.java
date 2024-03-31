package com.lifepill.possystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierCompanyDTO {
    private long companyId;
    private String companyName;
    private String companyAddress;
    private String companyContact;
    private String companyEmail;
    private String companyDescription;
    private String companyImage;
    private String companyStatus;
    private String companyRating;
    private String companyBank;
    private String companyAccountNumber;
}
