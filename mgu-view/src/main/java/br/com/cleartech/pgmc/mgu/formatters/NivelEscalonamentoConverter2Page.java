package br.com.cleartech.pgmc.mgu.formatters;

import org.springframework.core.convert.converter.Converter;

import br.com.cleartech.pgmc.mgu.entities.NivelEscalonamento;

public class NivelEscalonamentoConverter2Page implements Converter<NivelEscalonamento, String> {

	@Override
	public String convert( NivelEscalonamento source ) {
		return String.valueOf( source.getId() );
	}

}
