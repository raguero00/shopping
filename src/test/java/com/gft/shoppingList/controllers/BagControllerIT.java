package com.gft.shoppingList.controllers;

import com.gft.shoppingList.AbstractIntegrationTest;
import com.gft.shoppingList.domain.dto.Bag;
import com.gft.shoppingList.services.BagService;
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

import static com.gft.shoppingList.utils.TestData.Dto.testBag1;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
public class BagControllerIT extends AbstractIntegrationTest {

	private static final String URI = "/api/v1/bags";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private BagService bagService;

	@Autowired
	public BagControllerIT(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	public void testThatCreateBagReturnsHttpStatus201Created() throws Exception {
		final Bag bag = testBag1();

		mockMvc.perform(MockMvcRequestBuilders
				.post(URI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bag))
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
	}

	@Test
	public void testThatCreateBagReturnsCreatedBag() throws Exception {
		final Bag bag = testBag1();

		mockMvc.perform(MockMvcRequestBuilders
				.post(URI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(bag))
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.id").exists())
			.andExpect(jsonPath("$.name").value(bag.getName()))
			.andExpect(jsonPath("$.description").value(bag.getDescription()))
			.andExpect(jsonPath("$.description").value(bag.getDescription()))
			.andExpect(jsonPath("$.status").value(bag.getStatus()));
//			.andExpect(jsonPath("$.creationDate").value(shoppingList.getCreationDate()));
	}

	@Test
	public void testThatRetrieveBagByIdReturnsHttpStatusCode404WhenIdNotExists() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get(URI + "/123123123")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}

	@Test
	public void testThatRetrieveBagByIdReturnsHttpStatusCode200WhenIdExist() throws Exception {
		final Bag bag = testBag1();
		final Bag savedBag = bagService.create(bag);

		mockMvc.perform(MockMvcRequestBuilders
				.get(URI + "/" + savedBag.getId())
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*").exists())
			.andExpect(jsonPath("$.name").value(bag.getName()))
			.andExpect(jsonPath("$.description").value(bag.getDescription()))
			.andExpect(jsonPath("$.status").value(bag.getStatus()));
	}

	@Test
	public void testThatListBagsReturnsHttpStatus200() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get(URI)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}

	@Test
	public void testThatListBagsReturnsListOfBags() throws Exception {
		final Bag bag = testBag1();
		bagService.create(bag);

		mockMvc.perform(MockMvcRequestBuilders
				.get(URI)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*").exists())
			.andExpect(jsonPath("$.[0].name").value(bag.getName()))
			.andExpect(jsonPath("$.[0].description").value(bag.getDescription()))
			.andExpect(jsonPath("$.[0].status").value(bag.getStatus()));
	}

	@Test
	public void testThatDeleteBagReturnsHttp204WhenBagDoesntExist() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.delete(URI + "/12345"))
			.andExpect(status().isNoContent());
	}

	@Test
	public void testThatDeleteBagReturnsHttp204WhenExistingBagIsDeleted() throws Exception {
		// Make sure there is no data at all
		mockMvc.perform(MockMvcRequestBuilders
				.get(URI)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string("[]"));

		// Create new bag and insert it into DB
		final Bag bag = testBag1();
		final Bag savedBag = bagService.create(bag);

		// Check data is present in table
		mockMvc.perform(MockMvcRequestBuilders
				.get(URI)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.*").exists())
			.andExpect(jsonPath("$.[0].name").value(savedBag.getName()));

		// Delete data from database
		mockMvc.perform(MockMvcRequestBuilders
				.delete(URI + "/" + savedBag.getId()))
			.andExpect(status().isNoContent());

		// Check database is back to initial state
		mockMvc.perform(MockMvcRequestBuilders
				.get(URI)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string("[]"));
	}
}
