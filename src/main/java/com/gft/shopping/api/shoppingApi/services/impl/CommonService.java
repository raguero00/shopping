package com.gft.shopping.api.shoppingApi.services.impl;

import com.gft.shopping.api.shoppingApi.domain.dto.PagedResponse;

public interface CommonService<T> {

	PagedResponse<T> getPagedList(int pageNumber, int pageSize, String orderBy, String sortDirection);
}
