package com.gft.shoppingList.mappers;

import com.gft.shoppingList.domain.dto.ShoppingList;
import com.gft.shoppingList.domain.entities.ShoppingListEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListMapperImpl implements Mapper<ShoppingListEntity, ShoppingList> {

	private final ModelMapper modelMapper;

	public ShoppingListMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public ShoppingList mapTo(ShoppingListEntity shoppingListEntity) {
		return modelMapper.map(shoppingListEntity, ShoppingList.class);
	}

	@Override
	public ShoppingListEntity mapFrom(ShoppingList shoppingList) {
		return modelMapper.map(shoppingList, ShoppingListEntity.class);
	}

}