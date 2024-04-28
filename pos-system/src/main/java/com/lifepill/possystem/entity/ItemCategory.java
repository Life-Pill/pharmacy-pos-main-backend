package com.lifepill.possystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * The type Item category.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item_category")
public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private long categoryId;

    @Column(name = "category_name", length = 100, nullable = false)
    private String categoryName;

    @Column(name = "category_description", length = 100)
    private String categoryDescription;

    @Column(name = "category_image")
    private String categoryImage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemCategory")
    private Set<Item> items;

}
