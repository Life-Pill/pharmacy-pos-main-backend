package com.lifepill.possystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
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
    @Lob
    @Column(name = "branch_image")
    private byte[] branchImage;
    @Column(name = "branch_status", columnDefinition = "BOOLEAN default false")
    private boolean branchStatus;
    @Column(name = "branch_location", length = 20)
    private String branchLocation;
    @Column(name = "branch_created_on")
    private String branchCreatedOn;
    @Column(name = "branch_created_by")
    private String branchCreatedBy;

/*
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "branch")
    //@OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    private Set<Employer> employers;
*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branch", fetch = FetchType.EAGER)
    private Set<Employer> employers;


/*    public Branch(int branchId, String branchName, String branchAddress, String branchContact, String branchFax, String branchEmail, String branchDescription, byte[] branchImage, boolean branchStatus, String branchLocation, String branchCreatedOn, String branchCreatedBy) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchAddress = branchAddress;
        this.branchContact = branchContact;
        this.branchFax = branchFax;
        this.branchEmail = branchEmail;
        this.branchDescription = branchDescription;
        this.branchImage = branchImage;
        this.branchStatus = branchStatus;
        this.branchLocation = branchLocation;
        this.branchCreatedOn = branchCreatedOn;
        this.branchCreatedBy = branchCreatedBy;
    }*/

//branch manager
    //employee count
    //branch monthly sales
}
