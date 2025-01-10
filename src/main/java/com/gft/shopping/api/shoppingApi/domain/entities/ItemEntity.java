package com.gft.shopping.api.shoppingApi.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "items")
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;

	private String description;

	@Column(name = "original_barcode")
	private String originalBarcode;

	@Column(name = "secondary_barcode")
	private String secondaryBarcode;

	@Column(name = "original_amount")
	private BigDecimal originalAmount;

	@Column(name = "secondary_amount")
	private BigDecimal secondaryAmount;

	@Column(name = "receipt_number")
	private String receiptNumber;

	@Column(name = "alternative_receipt_number")
	private String alternateReceiptNumber;

	@Column(name = "store_name")
	private String storeName;

	@Column(name = "creation_date")
	private LocalDateTime creationDate;

	private int status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bag_id")
	private BagEntity bag;

}
