package br.com.cleartech.pgmc.mgu.repositories;

import org.springframework.data.repository.Repository;

import br.com.cleartech.pgmc.mgu.entities.Prestadora;

public interface PrestadoraRepository extends Repository<Prestadora, Long> {

	public Prestadora prestadoraPorUsername( String dcUsername );

	public Prestadora findOne( Long id );

}
