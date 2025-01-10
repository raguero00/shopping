package com.gft.shopping.api.shoppingApi.services.impl;

import com.gft.shopping.api.shoppingApi.domain.dto.ShoppingList;
import com.gft.shopping.api.shoppingApi.domain.entities.ShoppingListEntity;
import com.gft.shopping.api.shoppingApi.exception.ResourceNotFoundException;
import com.gft.shopping.api.shoppingApi.mappers.Mapper;
import com.gft.shopping.api.shoppingApi.repositories.ShoppingListRepository;
import com.gft.shopping.api.shoppingApi.services.ShoppingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

	private static final Logger logger = LoggerFactory.getLogger(ShoppingListServiceImpl.class);

	private final ShoppingListRepository shoppingListRepository;
	private final Mapper<ShoppingListEntity, ShoppingList> shoppingListMapper;

	public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository, Mapper<ShoppingListEntity, ShoppingList> shoppingListMapper) {
		this.shoppingListRepository = shoppingListRepository;
		this.shoppingListMapper = shoppingListMapper;
	}

	@Override
	public ShoppingList create(ShoppingList shoppingList) {
		final ShoppingListEntity shoppingListEntity = shoppingListMapper.mapFrom(shoppingList);
		final ShoppingListEntity savedShoppingListEntity = shoppingListRepository.save(shoppingListEntity);

		logger.info("Saved Shopping List with id = {}", savedShoppingListEntity.getId());
		return shoppingListMapper.mapTo(savedShoppingListEntity);
	}

	@Override
	public ShoppingList findById(Long id) {
		final ShoppingListEntity foundShoppingList = shoppingListRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(
			String.format("Shopping List doesn't exist with given id: %s", id)
		));
		return shoppingListMapper.mapTo(foundShoppingList);
	}

	@Override
	public List<ShoppingList> listShoppingLists() {
		final List<ShoppingListEntity> foundShoppingLists = shoppingListRepository.findAll();
		return foundShoppingLists.stream().map(shoppingListMapper::mapTo).collect(Collectors.toList());
	}

	@Override
	public ShoppingList update(Long id, ShoppingList shoppingList) {
		ShoppingListEntity foundShoppingList = shoppingListRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Shopping List doesn't exist with given id: %s", id)
			));

		foundShoppingList.setName(shoppingList.getName());
		foundShoppingList.setDescription(shoppingList.getDescription());
		foundShoppingList.setStatus(shoppingList.getStatus());

		ShoppingListEntity updatedShoppingList = shoppingListRepository.save(foundShoppingList);
		return shoppingListMapper.mapTo(updatedShoppingList);
	}

	@Override
	public void delete(Long id) {
		ShoppingListEntity foundShoppingList = shoppingListRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Shopping List doesn't exist with given id: %s", id)
			));

		shoppingListRepository.deleteById(id);
	}

}
