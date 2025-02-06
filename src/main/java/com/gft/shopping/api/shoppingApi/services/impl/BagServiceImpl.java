package com.gft.shopping.api.shoppingApi.services.impl;

import com.gft.shopping.api.shoppingApi.domain.dto.Bag;
import com.gft.shopping.api.shoppingApi.domain.dto.PagedResponse;
import com.gft.shopping.api.shoppingApi.domain.entities.BagEntity;
import com.gft.shopping.api.shoppingApi.domain.entities.ItemEntity;
import com.gft.shopping.api.shoppingApi.exception.ItemIsAlreadyAssignedException;
import com.gft.shopping.api.shoppingApi.exception.ResourceNotFoundException;
import com.gft.shopping.api.shoppingApi.mappers.Mapper;
import com.gft.shopping.api.shoppingApi.repositories.BagRepository;
import com.gft.shopping.api.shoppingApi.repositories.ItemRepository;
import com.gft.shopping.api.shoppingApi.services.BagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BagServiceImpl implements BagService {

	@Value("${bag.resourceNotFound.error.message}")
	private String bagNotFoundResourceError;

	@Value("${item.resourceNotFound.error.message}")
	private String itemNotFoundResourceError;

	private final BagRepository bagRepository;
//	private final ItemRepository itemRepository;
	private Mapper<BagEntity, Bag> bagMapper;

	@Autowired
	public BagServiceImpl(BagRepository bagRepository, Mapper<BagEntity, Bag> bagMapper) {
		this.bagRepository = bagRepository;
//		this.itemRepository = itemRepository;
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
				String.format(bagNotFoundResourceError, id)));
		return bagMapper.mapTo(foundBag);
	}

	@Override
	public List<Bag> listBags() {
		final List<BagEntity> foundBags = bagRepository.findAll();
		return foundBags.stream().map(bagMapper::mapTo).collect(Collectors.toList());
	}

	@Override
	public PagedResponse<Bag> getPagedList(int pageNumber, int pageSize, String orderBy, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<BagEntity> bags = bagRepository.findAll(pageable);
		List<BagEntity> pagedList = bags.getContent();
		List<Bag> content = pagedList.stream().map(bagMapper::mapTo).collect(Collectors.toList());

		PagedResponse<Bag> pagedResponse = new PagedResponse<Bag>();
		pagedResponse.setContent(content);
		pagedResponse.setNumber(bags.getNumber());
		pagedResponse.setSize(bags.getSize());
		pagedResponse.setTotalElements(bags.getTotalElements());
		pagedResponse.setTotalPages(bags.getTotalPages());
		pagedResponse.setLast(bags.isLast());

		return pagedResponse;
	}

	@Override
	public Bag update(Long id, final Bag bag) {
		BagEntity foundBag = bagRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(
				String.format(bagNotFoundResourceError, id)));

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
				String.format(bagNotFoundResourceError, id)));

		bagRepository.deleteById(id);
	}

//	@Override
//	public Bag addItemToBag(Long idBag, Long idItem) {
//		BagEntity bagEntity = bagRepository.findById(idBag)
//			.orElseThrow(() -> new ResourceNotFoundException(
//				String.format(bagNotFoundResourceError, idBag)));
//
//		ItemEntity itemEntity = itemRepository.findById(idItem)
//			.orElseThrow(() -> new ResourceNotFoundException(
//				String.format(itemNotFoundResourceError, idItem)));
//
//		if (Objects.nonNull(itemEntity.getBag()))
//			throw new ItemIsAlreadyAssignedException(idItem, itemEntity.getBag().getId());
//
//		bagEntity.addItem(itemEntity);
//
//		return bagMapper.mapTo(bagEntity);
//	}

//	@Override
//	public Bag removeItemFromBag(Long idBag, Long idItem) {
//		BagEntity bagEntity = bagRepository.findById(idBag)
//			.orElseThrow(() -> new ResourceNotFoundException(
//				String.format(bagNotFoundResourceError, idBag)));
//
//		ItemEntity itemEntity = itemRepository.findById(idItem)
//			.orElseThrow(() -> new ResourceNotFoundException(
//				String.format(itemNotFoundResourceError, idItem)));
//
//		bagEntity.removeItem(itemEntity);
//		return bagMapper.mapTo(bagEntity);
//	}

}
