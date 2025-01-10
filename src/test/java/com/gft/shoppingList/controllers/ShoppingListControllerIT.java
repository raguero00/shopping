package com.gft.shoppingList.controllers;

import com.gft.shoppingList.AbstractIntegrationTest;
import com.gft.shoppingList.domain.dto.ShoppingList;
import com.gft.shoppingList.services.ShoppingListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static com.gft.shoppingList.utils.TestData.Dto.testShoppingList;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ShoppingListControllerIT extends AbstractIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ShoppingListService shoppingListService;

	@Test
	public void testThatShoppingListIsCreated() throws Exception {
		final ShoppingList shoppingList = testShoppingList();

		mockMvc.perform(MockMvcRequestBuilders
				.put("/api/v1/shoppingLists")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(shoppingList))
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.name").value(shoppingList.getName()))
			.andExpect(jsonPath("$.description").value(shoppingList.getDescription()))
			.andExpect(jsonPath("$.status").value(shoppingList.getStatus()));
//			.andExpect(jsonPath("$.creationDate").value(shoppingList.getCreationDate()));
	}

	@Test
	public void testThatRetrieveShoppingListReturnsHttp404WhenShoppingListIdIsInvalid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/v1/shoppingLists/12121212121212"))
			.andExpect(status().isNotFound());
	}

	@Test
	public void testThatRetrieveShoppingListReturnsHttp200WhenShoppingListIdIsValid() throws Exception {
		final ShoppingList shoppingList = testShoppingList();
		final ShoppingList savedShoppingList = shoppingListService.create(shoppingList);

		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/v1/shoppingLists/" + savedShoppingList.getId()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.name").value(shoppingList.getName()))
			.andExpect(jsonPath("$.description").value(shoppingList.getDescription()))
			.andExpect(jsonPath("$.status").value(shoppingList.getStatus()));
	}

	@Test
	public void testThatListShoppingListsReturnsHttp200EmptyListWhenNoShoppingListsExist() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/v1/shoppingLists"))
			.andExpect(status().isOk())
			.andExpect(content().string("[]"));
	}

	@Test
	public void testThatListShoppingListsReturnsHttp200AndShoppingListsWhenShoppingListsExist() throws Exception {
		final ShoppingList shoppingList = testShoppingList();
		shoppingListService.create(shoppingList);

		mockMvc.perform(MockMvcRequestBuilders
				.get("/api/v1/shoppingLists"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*").exists())
			.andExpect(jsonPath("$.[0].name").value(shoppingList.getName()))
			.andExpect(jsonPath("$.[0].description").value(shoppingList.getDescription()))
			.andExpect(jsonPath("$.[0].status").value(shoppingList.getStatus()));
	}

//	private String asJsonString(Object object) {
//		final ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.registerModule(new JavaTimeModule());
//
//		try {
//			return objectMapper.writeValueAsString(object);
//		} catch (JsonProcessingException ex) {
//			ex.printStackTrace();
//		}
//		return null;
//	}

}