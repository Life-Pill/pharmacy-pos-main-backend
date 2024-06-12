package com.lifepill.possystem.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BranchNewUpdateDTO {
    private long branchId;
    private String branchName;
    private String branchAddress;
    private String branchContact;
    private String branchFax;
    private String branchEmail;
    private String branchDescription;
    private boolean branchStatus;
    private String branchLocation;
    private String branchCreatedOn;
    private String branchCreatedBy;
}
