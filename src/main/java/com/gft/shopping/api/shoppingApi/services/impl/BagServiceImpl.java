package com.gft.shopping.api.shoppingApi.services.impl;

import com.gft.shopping.api.shoppingApi.domain.dto.Bag;
import com.gft.shopping.api.shoppingApi.domain.entities.BagEntity;
import com.gft.shopping.api.shoppingApi.exception.ResourceNotFoundException;
import com.gft.shopping.api.shoppingApi.mappers.Mapper;
import com.gft.shopping.api.shoppingApi.repositories.BagRepository;
import com.gft.shopping.api.shoppingApi.services.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BagServiceImpl implements BagService {

//	@Value("${resourcenotfoundexception.general.id.bag.message}")
//	private String generalErrorId;

	private final BagRepository bagRepository;
	private Mapper<BagEntity, Bag> bagMapper;

	@Autowired
	public BagServiceImpl(BagRepository bagRepository, Mapper<BagEntity, Bag> bagMapper) {
		this.bagRepository = bagRepository;
		this.bagMapper = bagMapper;
	}

	@Override
	public boolean isBagExists(final Bag bag) {
		return bagRepository.existsById(bag.getId());
	}

	@Override
	public Bag create(final Bag bag) {
		BagEntity bagEntity = bagMapper.mapFrom(bag);
		BagEntity savedBagEntity = bagRepository.save(bagEntity);

		return bagMapper.mapTo(savedBagEntity);
	}

	@Override
	public Bag findById(Long id) {
		BagEntity foundBag = bagRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Bag doesn't exist with given id: %s", id)));
		return bagMapper.mapTo(foundBag);
	}

	@Override
	public List<Bag> listBags() {
		final List<BagEntity> foundBags = bagRepository.findAll();
		return foundBags.stream().map(bagMapper::mapTo).collect(Collectors.toList());
	}

	@Override
	public Bag update(Long id, final Bag bag) {
		BagEntity foundBag = bagRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Bag doesn't exist with given id: %s", id)));

		foundBag.setName(bag.getName());
		foundBag.setDescription(bag.getDescription());
		foundBag.setMaxAmount(bag.getMaxAmount());
		foundBag.setStatus(bag.getStatus());

		BagEntity updatedBag = bagRepository.save(foundBag);
		return bagMapper.mapTo(updatedBag);
	}

	@Override
	public void delete(Long id) {
		BagEntity foundBag = bagRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format("Bag doesn't exist with given id: %s", id)));

		bagRepository.deleteById(id);
	}

}
