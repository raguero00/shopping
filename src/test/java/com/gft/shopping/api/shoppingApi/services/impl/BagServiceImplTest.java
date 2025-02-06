package com.gft.shopping.api.shoppingApi.services.impl;

import com.gft.shopping.api.shoppingApi.domain.dto.Bag;
import com.gft.shopping.api.shoppingApi.domain.entities.BagEntity;
import com.gft.shopping.api.shoppingApi.mappers.BagMapperImpl;
import com.gft.shopping.api.shoppingApi.mappers.Mapper;
import com.gft.shopping.api.shoppingApi.repositories.BagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static com.gft.shopping.api.shoppingApi.utils.TestData.Dto.testBag1;
import static com.gft.shopping.api.shoppingApi.utils.TestData.Dto.testBag1WithoutIdAndDates;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BagServiceImplTest {

	@Mock
	private BagRepository bagRepository;

	@Mock
	private Mapper<BagEntity, Bag> bagMapper;

	private BagServiceImpl underTest;

	@BeforeEach
	public void setUp() {
		bagMapper = new BagMapperImpl(new ModelMapper());
		underTest = new BagServiceImpl(bagRepository, bagMapper);
	}

	@Test
	public void testThatBagIsSaved() {
		Bag bag = testBag1WithoutIdAndDates();
		BagEntity bagEntity = bagMapper.mapFrom(bag);

		when(bagRepository.save(bagEntity)).thenReturn(bagEntity);
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
	public void testThatFindByIdReturnsBag() {
		Bag bag = testBag1WithoutIdAndDates();
		BagEntity bagEntity = bagMapper.mapFrom(bag);

		when(bagRepository.findById(eq(1L))).thenReturn(Optional.ofNullable(bagEntity));

		Bag result = underTest.findById(1L);
		assertThat(bag).isNotNull();
	}

	@Test
	public void testThatUpdateBagReturnsUpdatedBag() {
		Bag bag = testBag1WithoutIdAndDates();
		BagEntity bagEntity = bagMapper.mapFrom(bag);

		when(bagRepository.findById(eq(1L))).thenReturn(Optional.ofNullable(bagEntity));
		when(bagRepository.save(bagEntity)).thenReturn(bagEntity);

		Bag savedBag = underTest.update(1L, bag);
		assertThat(savedBag).isNotNull();
	}

	@Test
	public void testThatDeleteBagDeletesBag() {
		Bag bag = testBag1WithoutIdAndDates();
		BagEntity bagEntity = bagMapper.mapFrom(bag);

		when(bagRepository.findById(eq(1L))).thenReturn(Optional.ofNullable(bagEntity));

		underTest.delete(1L);
		verify(bagRepository, times(1)).deleteById(eq(1L));
	}

}
