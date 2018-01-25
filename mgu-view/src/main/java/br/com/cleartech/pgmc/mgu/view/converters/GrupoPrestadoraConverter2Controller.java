package br.com.cleartech.pgmc.mgu.view.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import br.com.cleartech.pgmc.mgu.entities.GrupoPrestadora;
import br.com.cleartech.pgmc.mgu.repositories.GrupoPrestadoraRepository;
import br.com.cleartech.pgmc.mgu.utils.StringUtils;

public class GrupoPrestadoraConverter2Controller implements Converter<String, GrupoPrestadora> {

	@Autowired
	private GrupoPrestadoraRepository repository;

	@Override
	public GrupoPrestadora convert( String source ) {
		if ( StringUtils.isEmpty( source ) ) {
			return null;
		}

		return repository.findOne( Long.parseLong( source ) );
	}

}
