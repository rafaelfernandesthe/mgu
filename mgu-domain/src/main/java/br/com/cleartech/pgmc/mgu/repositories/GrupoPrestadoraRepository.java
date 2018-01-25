package br.com.cleartech.pgmc.mgu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.cleartech.pgmc.mgu.entities.GrupoPrestadora;

public interface GrupoPrestadoraRepository extends CrudRepository<GrupoPrestadora, Long> {

	public List<GrupoPrestadora> findAllByOrderByNoGrupoPrestadoraAsc();

}
