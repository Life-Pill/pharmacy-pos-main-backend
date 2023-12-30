package com.lifepill.possystem.repo;
import com.lifepill.possystem.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item,Integer> {
    List<Item> findAllByItemNameEqualsAndStockEquals(String itemName, boolean b);
    List<Item> findAllByStockEquals(boolean activeStatus);
    Page<Item> findAllByStockEquals(boolean activeStatus, Pageable pageable);
    int countAllByStockEquals(boolean activeStatus);
    List<Item> findAllByItemBarCodeEquals(String itemBarCode);
}
