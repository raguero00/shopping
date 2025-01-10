package com.gft.shoppingList.repositories;

import com.gft.shoppingList.domain.entities.BagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagRepository extends JpaRepository<BagEntity, Long> {
}
