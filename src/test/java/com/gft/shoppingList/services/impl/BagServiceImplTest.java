package com.gft.shoppingList.services.impl;

import com.gft.shoppingList.domain.dto.Bag;
import com.gft.shoppingList.domain.entities.BagEntity;
import com.gft.shoppingList.mappers.Mapper;
import com.gft.shoppingList.repositories.BagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static com.gft.shoppingList.utils.TestData.Dto.testBag1;
import static com.gft.shoppingList.utils.TestData.Dto.testBag1WithoutIdAndDates;
import static com.gft.shoppingList.utils.TestData.Entity.testBagEntity1WithoutIdAndDates;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BagServiceImplTest {

	@Mock
	private BagRepository bagRepository;

	@Mock
	private ObjectMapper objectMapper;

	@Mock
	private Mapper<BagEntity, Bag> bagMapper;

	@InjectMocks
	private BagServiceImpl underTest;

	@Test
	public void testThatBagIsSaved() {
		Bag bag = testBag1WithoutIdAndDates();
		bag.setId(1L);
		BagEntity bagEntity = testBagEntity1WithoutIdAndDates();
		bagEntity.setId(1L);

		lenient().when(bagRepository.save(eq(bagEntity))).thenReturn(bagEntity);

		Bag result = underTest.create(bag);
		Assertions.assertEquals(bag, result);
	}

	@Test
	public void testThatBagExistsReturnsFalseWhenBagDoesntExist() {
		when(bagRepository.existsById(any())).thenReturn(false);
		final boolean result = underTest.isBagExists(testBag1());
		assertEquals(false, result);
	}

	@Test
	public void testThatBagExistsReturnsTrueWhenBagDoesExist() {
		when(bagRepository.existsById(any())).thenReturn(true);
		final boolean result = underTest.isBagExists(testBag1());
		assertEquals(true, result);
	}

	@Test
	public void testThatDeleteBagDeletesBag() {
		underTest.delete(1L);
		verify(bagRepository, times(1)).deleteById(eq(1L));
	}
}
