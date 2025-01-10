package com.gft.shopping.api.shoppingApi.mappers;

import com.gft.shopping.api.shoppingApi.domain.dto.Item;
import com.gft.shopping.api.shoppingApi.domain.entities.ItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemMapperImpl implements Mapper<ItemEntity, Item> {

	private final ModelMapper modelMapper;

	public ItemMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public Item mapTo(ItemEntity itemEntity) {
		return modelMapper.map(itemEntity, Item.class);
	}

	@Override
	public ItemEntity mapFrom(Item item) {
		return modelMapper.map(item, ItemEntity.class);
	}

}