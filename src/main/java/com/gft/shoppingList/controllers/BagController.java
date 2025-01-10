package com.gft.shoppingList.controllers;

import com.gft.shoppingList.domain.dto.Bag;
import com.gft.shoppingList.services.BagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bags")
public class BagController {

	private final BagService bagService;

	public BagController(BagService bagService) {
		this.bagService = bagService;
	}

	@PostMapping
	public ResponseEntity<Bag> createBag(@RequestBody Bag bag) {
		Bag savedBag = bagService.create(bag);
		return new ResponseEntity<Bag>(savedBag, HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Bag> retrieveBag(@PathVariable final Long id) {
		final Bag foundBag = bagService.findById(id);
		return ResponseEntity.ok(foundBag);
	}

	@GetMapping
	public ResponseEntity<List<Bag>> listBags() {
		List<Bag> bags = bagService.listBags();
		return ResponseEntity.ok(bags);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Bag> updateBag(@PathVariable final Long id, @RequestBody final Bag bag) {
		Bag updatedBag = bagService.update(id, bag);
		return ResponseEntity.ok(updatedBag);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteBag(@PathVariable final Long id) {
		bagService.delete(id);
		return ResponseEntity.ok("Bag deleted successfully!");
	}
}
