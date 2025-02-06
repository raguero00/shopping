package com.gft.shopping.api.shoppingApi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {

	private String firstName;
	private String lastName;
	private String location;
	private String language;

}
