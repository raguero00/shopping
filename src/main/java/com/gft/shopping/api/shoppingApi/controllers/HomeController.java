package com.gft.shopping.api.shoppingApi.controllers;

//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

//	@GetMapping("/home")
//	public String home(Authentication authentication) {
//		String user = authentication != null ? authentication.getName() : "User";
//		return "Hello, " + user + " Nice to meet you";
//	}

	@GetMapping("/home")
	public String home() {
		String user = "User";
		return "Hello, " + user + " Nice to meet you";
	}

}
