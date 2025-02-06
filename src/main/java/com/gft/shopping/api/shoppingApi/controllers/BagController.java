package com.gft.shopping.api.shoppingApi.controllers;

import com.gft.shopping.api.shoppingApi.domain.dto.Bag;
import com.gft.shopping.api.shoppingApi.domain.dto.PagedResponse;
import com.gft.shopping.api.shoppingApi.services.BagService;
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

	@PostMapping(consumes = {"application/json"})
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

	@GetMapping("/paged")
	public ResponseEntity<PagedResponse<Bag>> getPagedBags(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNumber,
																												 @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
																												 @RequestParam(value = "sortBy", defaultValue = "id", required = false) String orderBy,
																												 @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDirection) {
		PagedResponse<Bag> response = bagService.getPagedList(pageNumber, pageSize, orderBy, sortDirection);
		return ResponseEntity.ok(response);
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
