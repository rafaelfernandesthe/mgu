package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.entities.ParametroSistema;

public interface ParametroSistemaService {

	ParametroSistema findByGrupoPrestadoraId( Long idGrupoPrestadora );

}
