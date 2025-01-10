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
		final ShoppingList foundShoppingList = shoppingListService.findById(id);
		return ResponseEntity.ok(foundShoppingList);
	}

	@GetMapping
	public ResponseEntity<List<ShoppingList>> listShoppingLists() {
		return ResponseEntity.ok(shoppingListService.listShoppingLists());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ShoppingList> updateShoppingList(@PathVariable(value = "id") Long id,
																												 @RequestBody ShoppingList shoppingList) {
		final ShoppingList updatedShoppingList = shoppingListService.update(id, shoppingList);
		return ResponseEntity.ok(updatedShoppingList);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteShoppingList(@PathVariable(value = "id") Long id) {
		shoppingListService.delete(id);
		return ResponseEntity.ok("ShoppingList deleted successfully!");
	}

}
