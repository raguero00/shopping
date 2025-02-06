package com.gft.shopping.api.shoppingApi.services;

import com.gft.shopping.api.shoppingApi.domain.dto.Item;
import com.gft.shopping.api.shoppingApi.domain.dto.PagedResponse;
import com.gft.shopping.api.shoppingApi.services.impl.CommonService;

import java.util.List;

public interface ItemService extends CommonService<Item> {

	boolean isItemExists(final Long idBag, final Item item);

	Item create(final Long idBag, final Item item);

	List<Item> listItems(Long idBag);

	PagedResponse<Item> getPagedList(int pageNumber, int pageSize, String orderBy, String sortDirection);

	Item findById(Long idBag, Long idItem);

	Item update(Long idBag, Long idItem, final Item item);

	void delete(Long idBag, Long idItem);

}
