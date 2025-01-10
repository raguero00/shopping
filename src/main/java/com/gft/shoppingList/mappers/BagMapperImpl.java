package com.gft.shoppingList.mappers;

import com.gft.shoppingList.domain.dto.Bag;
import com.gft.shoppingList.domain.entities.BagEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BagMapperImpl implements Mapper<BagEntity, Bag> {

	private final ModelMapper modelMapper;

	public BagMapperImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public Bag mapTo(BagEntity bagEntity) {
		return modelMapper.map(bagEntity, Bag.class);
	}

	@Override
	public BagEntity mapFrom(Bag bag) {
		return modelMapper.map(bag, BagEntity.class);
	}

}