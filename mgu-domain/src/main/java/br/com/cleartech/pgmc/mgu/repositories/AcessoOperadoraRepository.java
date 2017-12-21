package br.com.cleartech.pgmc.mgu.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.cleartech.pgmc.mgu.entities.AcessoOperadora;
import br.com.cleartech.pgmc.mgu.enums.TipoOperadora;

public interface AcessoOperadoraRepository extends CrudRepository<AcessoOperadora, Long> {

	AcessoOperadora findByTipoOperadora( TipoOperadora tipoOperado );
}
