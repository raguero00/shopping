package com.gft.shopping.api.shoppingApi.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shopping_lists")
public class ShoppingListEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shopping_list_id")
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	private int status;
	private LocalDateTime creationDate;

	@ManyToMany
	@JoinTable(
		name = "bags_in_list",
		joinColumns = @JoinColumn(name = "shopping_list_id"),
		inverseJoinColumns = @JoinColumn(name = "bag_id")
	)
	private List<BagEntity> bags = new ArrayList<>();
}
