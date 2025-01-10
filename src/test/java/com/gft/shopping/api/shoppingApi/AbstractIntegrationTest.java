package com.gft.shopping.api.shoppingApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

public abstract class AbstractIntegrationTest {

	@Container
	@ServiceConnection
	public static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

	static {
		mySQLContainer.start();
	}

	protected String asJsonString(Object object) {
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());

		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}

