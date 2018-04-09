package br.com.cleartech.pgmc.mgu.services;

import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.Prestadora;

public interface PrestadoraService {

	Prestadora findById( Long id );

	public Prestadora prestadoraPorUsername( String dcUsername );

	public List<Prestadora> buscaPrestadorasDoGrupo( Long grupoPrestadoraId );
}
