package br.com.cleartech.pgmc.mgu.view.converters;

import org.springframework.core.convert.converter.Converter;

import br.com.cleartech.pgmc.mgu.entities.GrupoPrestadora;

public class GrupoPrestadoraConverter2Page implements Converter<GrupoPrestadora, String> {

	@Override
	public String convert( GrupoPrestadora source ) {
		return String.valueOf( source.getId() );
	}

}
