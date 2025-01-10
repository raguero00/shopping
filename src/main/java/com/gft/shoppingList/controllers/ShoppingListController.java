package com.gft.shoppingList.controllers;

import com.gft.shoppingList.domain.dto.ShoppingList;
import com.gft.shoppingList.services.ShoppingListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/shoppingLists")
public class ShoppingListController {

	private final ShoppingListService shoppingListService;

	public ShoppingListController(ShoppingListService shoppingListService) {
		this.shoppingListService = shoppingListService;
	}

	@PutMapping
	public ResponseEntity<ShoppingList> createShoppingList(@RequestBody final ShoppingList shoppingList) {
		final ShoppingList savedShoppingList = shoppingListService.create(shoppingList);
		return new ResponseEntity<ShoppingList>(savedShoppingList, HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<ShoppingList> retrieveShoppingList(@PathVariable final Long id) {
		final Optional<ShoppingList> foundShoppingList = shoppingListService.findById(id);
		return foundShoppingList.map(shoppingList -> new ResponseEntity<ShoppingList>(shoppingList, HttpStatus.OK))
			.orElse(new ResponseEntity<ShoppingList>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public ResponseEntity<List<ShoppingList>> listShoppingLists() {
		return new ResponseEntity<List<ShoppingList>>(shoppingListService.listShoppingLists(), HttpStatus.OK);
	}

}
