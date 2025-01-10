package com.gft.shoppingList.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.shoppingList.domain.dto.ShoppingList;
import com.gft.shoppingList.domain.entities.ShoppingListEntity;
import com.gft.shoppingList.mappers.Mapper;
import com.gft.shoppingList.repositories.ShoppingListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.gft.shoppingList.utils.TestData.Dto.testShoppingList;
import static com.gft.shoppingList.utils.TestData.Entity.testShoppingListEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoppingListServiceImplTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Mock
	private ShoppingListRepository shoppingListRepository;

	@Mock
	private Mapper<ShoppingListEntity, ShoppingList> shoppingListMapper;

	@InjectMocks
	private ShoppingListServiceImpl underTest;

	@Test
	public void testThatShoppingListIsSaved() {
		final ShoppingList shoppingList = testShoppingList();
		final ShoppingListEntity shoppingListEntity = testShoppingListEntity();

		when(shoppingListRepository.save(eq(shoppingListEntity))).thenReturn(shoppingListEntity);

		final ShoppingList result = underTest.create(shoppingList);
		assertEquals(shoppingList, result);
	}

	@Test
	public void testThatFindByIdReturnsEmptyWhenShoppingListIdIsInvalid() {
		final Long shoppingListId = Long.MIN_VALUE;
		when(shoppingListRepository.findById(eq(shoppingListId))).thenReturn(Optional.empty());

		final Optional<ShoppingList> result = underTest.findById(shoppingListId);
		assertEquals(Optional.empty(), result);
	}

	@Test
	public void testThatFindByIdReturnsShoppingListWhenShoppingListIdIsValid() {
		final ShoppingList shoppingList = testShoppingList();
		shoppingList.setId(1L);
		final ShoppingListEntity shoppingListEntity = testShoppingListEntity();
		shoppingListEntity.setId(1L);

		when(shoppingListRepository.findById(eq(shoppingList.getId()))).thenReturn(Optional.of(shoppingListEntity));

		final Optional<ShoppingList> result = underTest.findById(shoppingList.getId());
		assertEquals(Optional.of(shoppingList), result);
	}

	@Test
	public void testListShoppingListsReturnsEmptyWhenNoShoppingListExists() {
		when(shoppingListRepository.findAll()).thenReturn(new ArrayList<>());
		final List<ShoppingList> result = underTest.listShoppingLists();
		assertEquals(0, result.size());
	}

	@Test
//	@DisplayName("Happy Path Test: Returns one item after")
	public void testListShoppingListsReturnsShoppingListWhenExists() {
		final ShoppingListEntity shoppingListEntity = testShoppingListEntity();
		when(shoppingListRepository.findAll()).thenReturn(List.of(shoppingListEntity));

		final List<ShoppingList> result = underTest.listShoppingLists();
		assertEquals(1, result.size());
	}
}
