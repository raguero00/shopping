package com.gft.shoppingList.domain.entities;

import jakarta.persistence.*;
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
@Entity
@Table( name = "bags")
public class BagEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bag_id")
	private Long id;

	private String name;

	private String description;

	@Column(name = "max_amount"	)
	private Double maxAmount;

	private int status;

	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	@ManyToMany(mappedBy = "bags")
	private List<ShoppingListEntity> shoppingLists;

	@OneToMany(mappedBy = "bag", cascade = CascadeType.ALL)
	private List<ItemEntity> items;

}