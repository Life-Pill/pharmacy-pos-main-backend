package com.lifepill.possystem.repo.itemRepository;

import com.lifepill.possystem.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

/**
 * The interface Item category repository.
 */
//@RepositoryRestResource(collectionResourceRel = "itemCategory", path = "item-Category")
@Repository
@EnableJpaRepositories
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

}
