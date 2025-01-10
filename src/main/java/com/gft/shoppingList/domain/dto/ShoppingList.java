package com.gft.shoppingList.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingList {

	private Long id;
	private String name;
	private String description;
	private int status;
	private LocalDateTime creationDate;

}
