package com.gft.shopping.api.shoppingApi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagedResponse<T> {

	private List<T> content;
	private Integer totalPages;
	private Long totalElements;
	private Integer size;
	private Integer number;
	private Integer numberOfElements;
	private Boolean first;
	private Boolean last;

}