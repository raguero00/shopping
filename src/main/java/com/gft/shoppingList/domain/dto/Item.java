package com.gft.shoppingList.domain.dto;

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
public class Item {

	private Long id;
	private String description;
	private String originalBarcode;
	private String secondaryBarcode;
	private BigDecimal originalAmount;
	private BigDecimal secondaryAmount;
	private String receiptNumber;
	private String alternateReceiptNumber;
	private String storeName;
	private LocalDateTime creationDate;
	private int status;

}