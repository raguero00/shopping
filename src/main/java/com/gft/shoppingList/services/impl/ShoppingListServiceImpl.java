package com.gft.shoppingList.services.impl;

import com.gft.shoppingList.domain.dto.ShoppingList;
import com.gft.shoppingList.domain.entities.ShoppingListEntity;
import com.gft.shoppingList.mappers.Mapper;
import com.gft.shoppingList.repositories.ShoppingListRepository;
import com.gft.shoppingList.services.ShoppingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Optional<ShoppingList> findById(Long id) {
		final Optional<ShoppingListEntity> foundShoppingList = shoppingListRepository.findById(id);
		return foundShoppingList.map(shoppingListMapper::mapTo);
	}

	@Override
	public List<ShoppingList> listShoppingLists() {
		final List<ShoppingListEntity> foundShoppingLists = shoppingListRepository.findAll();
		return foundShoppingLists.stream().map(shoppingListMapper::mapTo).collect(Collectors.toList());
	}

	@Override
	public void update(Long id, ShoppingList shoppingList) {

	}

	@Override
	public void delete(Long id) {

	}

}
