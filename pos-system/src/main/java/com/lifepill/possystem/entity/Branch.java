package com.lifepill.possystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "branch")
@Builder
public class Branch {
    @Id
    @Column(name = "brach_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int branchId;
    @Column(name = "branch_name", length = 20, nullable = false)
    private String branchName;
    @Column(name = "branch_address", length = 100, nullable = false)
    private String branchAddress;
    @Column(name = "branch_contact", length = 12)
    private String branchContact;
    @Column(name = "branch_fax", length = 12)
    private String branchFax;
    @Column(name = "branch_email", length = 50)
    private String branchEmail;
    @Column(name = "branch_description", length = 100)
    private String branchDescription;
    @Column(name = "branch_image")
    private String branchImage;
    @Column(name = "branch_status", columnDefinition = "BOOLEAN default false")
    private boolean branchStatus;
    @Column(name = "branch_location", length = 20)
    private String branchLocation;
    @Column(name = "branch_created_on")
    private String branchCreatedOn;
    @Column(name = "branch_created_by")
    private String branchCreatedBy;


    //branch manager
    //employee count
    //branch monthly sales
}
