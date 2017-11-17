package br.com.cleartech.pgmc.mgu.repositories.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class Page extends PageRequest {
	private static final long serialVersionUID = 3315885049075179208L;

	public Page( int firsResult, int size, Direction direction, String... properties ) {
		super( getPageNumber( firsResult, size ), size, direction, properties );
	}

	public Page( int firsResult, int size, Sort sort ) {
		super( getPageNumber( firsResult, size ), size, sort );
	}

	public Page( int firsResult, int size ) {
		super( getPageNumber( firsResult, size ), size );
	}

	private static int getPageNumber( int firstResult, int pageSize ) {
		return firstResult == 0 ? 0 : firstResult / pageSize;
	}
}