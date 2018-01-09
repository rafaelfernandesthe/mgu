package br.com.cleartech.pgmc.mgu.view.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;
import br.com.cleartech.pgmc.mgu.utils.StringUtils;

public class UsuarioConverter2Controller implements Converter<String, Usuario> {

	@Autowired
	private UsuarioService service;

	@Override
	public Usuario convert( String source ) {
		if ( StringUtils.isEmpty( source ) ) {
			return null;
		}

		Usuario loaded = service.find( Long.parseLong( source ) );
		return loaded;
	}

}
