package com.lifepill.possystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * The type Branch.
 */
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
    private long branchId;
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
    @Column(name = "branch_profile_image_url")
    private String branchProfileImageUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branch", fetch = FetchType.EAGER)
    private Set<Employer> employers;

    @ManyToMany
    @JoinTable(
            name = "branch_item",
            joinColumns = @JoinColumn(name = "branch_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> items;


}
