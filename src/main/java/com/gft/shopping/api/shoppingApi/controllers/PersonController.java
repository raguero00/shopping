package com.gft.shopping.api.shoppingApi.controllers;

import com.gft.shopping.api.shoppingApi.domain.dto.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class PersonController {

	List<Person> people = Arrays.asList(
		new Person("Ricardo", "Aguero", "CR", "Spanish")
	);

	@GetMapping("/persons")
	public List<Person> getPeople() {
		return people;
	}
}
