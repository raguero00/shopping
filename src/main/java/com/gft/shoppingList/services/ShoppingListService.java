package com.gft.shoppingList.services;

import com.gft.shoppingList.domain.dto.ShoppingList;

import java.util.List;
import java.util.Optional;

public interface ShoppingListService {

	ShoppingList create(ShoppingList shoppingList);

	Optional<ShoppingList> findById(Long id);

	List<ShoppingList> listShoppingLists();

	void update(Long id, ShoppingList shoppingList);

	void delete(Long id);

}
