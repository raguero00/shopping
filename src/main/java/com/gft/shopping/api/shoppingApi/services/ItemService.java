package com.gft.shopping.api.shoppingApi.services;

import com.gft.shopping.api.shoppingApi.domain.dto.Item;

import java.util.List;

public interface ItemService {

	boolean isItemExists(final Long idBag, final Item item);

	Item create(final Long idBag, final Item item);

	List<Item> listItems(Long idBag);

	Item findById(Long idBag, Long idItem);

	Item update(Long idBag, Long idItem, final Item item);

	void delete(Long idBag, Long idItem);

}
