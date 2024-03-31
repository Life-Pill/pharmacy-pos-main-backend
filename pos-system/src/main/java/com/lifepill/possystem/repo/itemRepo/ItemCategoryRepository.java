package com.lifepill.possystem.repo.itemRepo;

import com.lifepill.possystem.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "itemCategory", path = "item-Category")
@Repository
@EnableJpaRepositories
public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

}
