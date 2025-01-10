package com.gft.shopping.api.shoppingApi.services.impl;

import com.gft.shopping.api.shoppingApi.domain.dto.Item;
import com.gft.shopping.api.shoppingApi.domain.entities.BagEntity;
import com.gft.shopping.api.shoppingApi.domain.entities.ItemEntity;
import com.gft.shopping.api.shoppingApi.exception.ResourceNotFoundException;
import com.gft.shopping.api.shoppingApi.exception.ShoppingListBagException;
import com.gft.shopping.api.shoppingApi.mappers.Mapper;
import com.gft.shopping.api.shoppingApi.repositories.BagRepository;
import com.gft.shopping.api.shoppingApi.repositories.ItemRepository;
import com.gft.shopping.api.shoppingApi.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

	private final BagRepository bagRepository;
	private final ItemRepository itemRepository;
	private final Mapper<ItemEntity, Item> itemMapper;

	@Autowired
	public ItemServiceImpl(BagRepository bagRepository, ItemRepository itemRepository, Mapper<ItemEntity, Item> itemMapper) {
		this.bagRepository = bagRepository;
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
	}

	@Override
	public boolean isItemExists(Long idBag, Item item)  {
		BagEntity bagEntity = bagRepository.findById(idBag)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Bag doesn't exist with given id: %s", idBag)));

		return itemRepository.existsById(item.getId());
	}

	@Override
	public Item create(Long idBag, Item item) {
		final BagEntity bagEntity = bagRepository.findById(idBag)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Bag doesn't exist with given id: %s", idBag)));

		ItemEntity itemEntity = itemMapper.mapFrom(item);
		itemEntity.setBag(bagEntity);
		ItemEntity savedEntity = itemRepository.save(itemEntity);

		return itemMapper.mapTo(savedEntity);
	}

	@Override
	public List<Item> listItems(Long idBag) {
		List<ItemEntity> items = itemRepository.findByBagId(idBag);
		return items.stream().map(itemMapper::mapTo).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long idBag, Long idItem) {
		final BagEntity bagEntity = bagRepository.findById(idBag)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Bag doesn't exist with given id: %s", idBag)));

		ItemEntity foundItem = itemRepository.findById(idItem)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Item doesn't exists with given id: %s", idItem)));

		if (!foundItem.getBag().getId().equals(bagEntity.getId()))
			throw new ShoppingListBagException(HttpStatus.BAD_REQUEST, "The item doesn't belong to the specified bag");

		return itemMapper.mapTo(foundItem);
	}

	@Override
	public Item update(Long idBag, Long idItem, Item item) {
		final BagEntity bagEntity = bagRepository.findById(idBag)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Bag doesn't exist with given id: %s", idBag)));

		ItemEntity foundItem = itemRepository.findById(idItem)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Item doesn't exists with given id: %s", idBag)));

		if (!foundItem.getBag().getId().equals(bagEntity.getId()))
			throw new ShoppingListBagException(HttpStatus.BAD_REQUEST, "The item doesn't belong to the specified bag");

		foundItem.setDescription(item.getDescription());
		foundItem.setOriginalBarcode(item.getOriginalBarcode());
		foundItem.setSecondaryBarcode(item.getSecondaryBarcode());
		foundItem.setOriginalAmount(item.getOriginalAmount());
		foundItem.setSecondaryAmount(item.getSecondaryAmount());
		foundItem.setReceiptNumber(item.getReceiptNumber());
		foundItem.setAlternateReceiptNumber(item.getAlternateReceiptNumber());
		foundItem.setStoreName(item.getStoreName());
		foundItem.setStatus(item.getStatus());

		ItemEntity updatedItem = itemRepository.save(foundItem);
		return itemMapper.mapTo(updatedItem);
	}

	@Override
	public void delete(Long idBag, Long idItem) {
		final BagEntity bagEntity = bagRepository.findById(idBag)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Bag doesn't exist with given id: %s", idBag)));

		ItemEntity foundItem = itemRepository.findById(idItem)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Item doesn't exists with given id: %s", idItem)));

		if (!foundItem.getBag().getId().equals(bagEntity.getId()))
			throw new ShoppingListBagException(HttpStatus.BAD_REQUEST, "The item doesn't belong to the specified bag");

		itemRepository.deleteById(idItem);
	}
}
