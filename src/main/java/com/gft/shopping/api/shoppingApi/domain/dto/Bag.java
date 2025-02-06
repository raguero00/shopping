package com.gft.shopping.api.shoppingApi.domain.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bag {

	private Long id;
	private String name;
	private String description;
	private Double maxAmount;
	private int status;
	private LocalDateTime creationDate;
	private List<ShoppingList> shoppingList;
	private List<Item> items;

}