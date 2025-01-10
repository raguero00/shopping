package com.gft.shopping.api.shoppingApi.repositories;

import com.gft.shopping.api.shoppingApi.domain.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

	public List<ItemEntity> findByBagId(long idBag);

	List<ItemEntity> storeNameEquals(String storeName);

}
