package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.entities.Prestadora;

public interface PrestadoraService {

	Prestadora findById( Long id );

	public Prestadora prestadoraPorUsername( String dcUsername );
}
