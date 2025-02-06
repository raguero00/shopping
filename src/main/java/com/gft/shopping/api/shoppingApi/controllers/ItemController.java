package com.gft.shopping.api.shoppingApi.controllers;

import com.gft.shopping.api.shoppingApi.domain.dto.Item;
import com.gft.shopping.api.shoppingApi.domain.dto.PagedResponse;
import com.gft.shopping.api.shoppingApi.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bags")
public class ItemController {

	private final ItemService itemService;

	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}

	@PostMapping("/{idBag}/items")
	public ResponseEntity<Item> createItem(@PathVariable(value = "idBag") Long idBag,
																				 @RequestBody Item item) {
		Item createdItem = itemService.create(idBag, item);
		return new ResponseEntity<Item>(createdItem, HttpStatus.CREATED);
	}

	@PutMapping("/{idBag}/items/{idItem}")
	public ResponseEntity<Item> updateItem(@PathVariable(value = "idBag") Long idBag,
																				 @PathVariable(value = "idItem") Long idItem,
																				 @RequestBody Item item) {

		Item updatedItem = itemService.update(idBag, idItem, item);
		return ResponseEntity.ok(updatedItem);
	}

	@GetMapping("/{idBag}/items/paged")
	public ResponseEntity<PagedResponse<Item>> getPagedBags(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNumber,
																												 @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
																												 @RequestParam(value = "sortBy", defaultValue = "id", required = false) String orderBy,
																												 @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDirection) {
		PagedResponse<Item> response = itemService.getPagedList(pageNumber, pageSize, orderBy, sortDirection);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{idBag}/items")
	public ResponseEntity<List<Item>> listItems(@PathVariable(value = "idBag") Long idBag) {
		return ResponseEntity.ok(itemService.listItems(idBag));
	}

	@GetMapping("/{idBag}/items/{idItem}")
	public ResponseEntity<Item> getItemById(@PathVariable(value = "idBag") Long idBag,
																					@PathVariable(value = "idItem") Long idItem) {

		Item foundItem = itemService.findById(idBag, idItem);
		return ResponseEntity.ok(foundItem);
	}

	@DeleteMapping("/{idBag}/items/{idItem}")
	public ResponseEntity<String> deleteItem(@PathVariable(value = "idBag") long idBag,
																					 @PathVariable(value = "idItem") long idItem) {
		itemService.delete(idBag, idItem);
		return new ResponseEntity<>("Item has been sucessfully deleted.", HttpStatus.OK);
	}
}
