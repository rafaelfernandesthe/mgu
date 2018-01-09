package br.com.cleartech.pgmc.mgu.view.converters;

import org.springframework.core.convert.converter.Converter;

import br.com.cleartech.pgmc.mgu.entities.Usuario;

public class UsuarioConverter2Page implements Converter<Usuario, String> {

	@Override
	public String convert( Usuario source ) {
		return String.valueOf( source.getId() );
	}

}
