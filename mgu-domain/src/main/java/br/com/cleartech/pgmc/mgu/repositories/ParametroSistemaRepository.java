package br.com.cleartech.pgmc.mgu.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.cleartech.pgmc.mgu.entities.ParametroSistema;

public interface ParametroSistemaRepository extends CrudRepository<ParametroSistema, Long> {

	ParametroSistema findByGrupoPrestadoraId( Long idGrupoPrestadora );

}
