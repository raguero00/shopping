package com.gft.shoppingList.exception;

import org.springframework.http.HttpStatus;

public class ShoppingListBagException extends RuntimeException {

	private HttpStatus status;
	private String message;

	public ShoppingListBagException(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

}
