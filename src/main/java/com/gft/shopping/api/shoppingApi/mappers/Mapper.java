package com.gft.shopping.api.shoppingApi.mappers;

public interface Mapper<A, B> {

	B mapTo(A a);

	A mapFrom(B b);
}
