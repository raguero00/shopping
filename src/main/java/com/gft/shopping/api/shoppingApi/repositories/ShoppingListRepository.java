package com.gft.shopping.api.shoppingApi.repositories;

import com.gft.shopping.api.shoppingApi.domain.entities.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, Long> {
}
