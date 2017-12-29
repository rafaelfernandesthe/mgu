package br.com.cleartech.pgmc.mgu.view.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import br.com.cleartech.pgmc.mgu.entities.NivelEscalonamento;
import br.com.cleartech.pgmc.mgu.services.NivelEscalonamentoService;
import br.com.cleartech.pgmc.mgu.utils.StringUtils;

public class NivelEscalonamentoConverter2Controller implements Converter<String, NivelEscalonamento> {

	@Autowired
	private NivelEscalonamentoService service;

	@Override
	public NivelEscalonamento convert( String source ) {
		if ( StringUtils.isEmpty( source ) ) {
			return null;
		}

		NivelEscalonamento loaded = service.findOne( Long.parseLong( source ) );
		return loaded;
	}

}
