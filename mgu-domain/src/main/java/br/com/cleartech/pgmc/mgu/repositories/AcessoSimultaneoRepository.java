package br.com.cleartech.pgmc.mgu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.cleartech.pgmc.mgu.entities.AcessoSimultaneo;

public interface AcessoSimultaneoRepository extends CrudRepository<AcessoSimultaneo, Long> {

	List<AcessoSimultaneo> findByUsername( String username );

}
