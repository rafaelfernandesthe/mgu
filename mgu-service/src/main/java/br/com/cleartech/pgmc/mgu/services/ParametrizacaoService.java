package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.enums.TipoOperadora;

public interface ParametrizacaoService {

	String findByDcParametro( String dcParametro );

	TipoOperadora retornaTipoOperadora( Long idOperadora );
}
