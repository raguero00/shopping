package com.gft.shopping.api.shoppingApi.exception;

public class ItemIsAlreadyAssignedException extends RuntimeException {

	public ItemIsAlreadyAssignedException(final Long  itemId, final Long bagId) {
		super(String.format("Item: %s is already assigned to Bag: %s", itemId, bagId));
	}
}
