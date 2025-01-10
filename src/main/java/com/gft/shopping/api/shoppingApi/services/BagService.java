package com.gft.shopping.api.shoppingApi.services;

import com.gft.shopping.api.shoppingApi.domain.dto.Bag;

import java.util.List;

public interface BagService {

	boolean isBagExists(final Bag bag);

	Bag create(final Bag bag);

	List<Bag> listBags();

	Bag findById(Long id);

	Bag update(Long id, final Bag bag);

	void delete(Long id);
}
