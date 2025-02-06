package com.gft.shopping.api.shoppingApi.exception;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

	private final int statusCode;
	private final String message;

}