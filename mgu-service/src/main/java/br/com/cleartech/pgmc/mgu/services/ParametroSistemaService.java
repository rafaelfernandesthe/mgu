package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.entities.ParametroSistema;

public interface ParametroSistemaService {

	ParametroSistema salvar( ParametroSistema parametroSistema );

	ParametroSistema findByGrupoPrestadoraId( Long idGrupoPrestadora );

}
