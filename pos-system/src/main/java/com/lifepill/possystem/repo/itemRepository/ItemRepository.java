package com.lifepill.possystem.repo.itemRepository;
import com.lifepill.possystem.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Item repository.
 */
@Repository
@EnableJpaRepositories
public interface ItemRepository extends JpaRepository<Item,Long> {
    /**
     * Find all by item name equals and stock equals list.
     *
     * @param itemName the item name
     * @param b        the b
     * @return the list
     */
    List<Item> findAllByItemNameEqualsAndStockEquals(String itemName, boolean b);

    /**
     * Find all by stock equals list.
     *
     * @param activeStatus the active status
     * @return the list
     */
    List<Item> findAllByStockEquals(boolean activeStatus);

    /**
     * Find all by stock equals page.
     *
     * @param activeStatus the active status
     * @param pageable     the pageable
     * @return the page
     */
    Page<Item> findAllByStockEquals(boolean activeStatus, Pageable pageable);

    /**
     * Count all by stock equals int.
     *
     * @param activeStatus the active status
     * @return the int
     */
    int countAllByStockEquals(boolean activeStatus);

    /**
     * Find all by item bar code equals list.
     *
     * @param itemBarCode the item bar code
     * @return the list
     */
    List<Item> findAllByItemBarCodeEquals(String itemBarCode);
}
