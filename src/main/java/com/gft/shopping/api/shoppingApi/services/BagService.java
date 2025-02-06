package com.gft.shopping.api.shoppingApi.services;

import com.gft.shopping.api.shoppingApi.domain.dto.Bag;
import com.gft.shopping.api.shoppingApi.domain.dto.PagedResponse;
import com.gft.shopping.api.shoppingApi.services.impl.CommonService;

import java.util.List;

public interface BagService extends CommonService<Bag> {

	boolean isBagExists(final Bag bag);

	Bag create(final Bag bag);

	List<Bag> listBags();

	PagedResponse<Bag> getPagedList(int pageNumber, int pageSize, String orderBy, String sortDirection);

	Bag findById(Long id);

	Bag update(Long id, final Bag bag);

	void delete(Long id);

//	Bag addItemToBag(Long idBag, Long idItem);
//
//	Bag removeItemFromBag(Long idBag, Long idItem);

}
