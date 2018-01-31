package br.com.cleartech.pgmc.mgu.services;

import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.ParametroSistema;

public interface ParametroSistemaService {

	ParametroSistema salvar( ParametroSistema parametroSistema );

	ParametroSistema findByGrupoPrestadoraId( Long idGrupoPrestadora );

	List<ParametroSistema> findAll();

}
