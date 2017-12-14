package br.com.cleartech.pgmc.mgu.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleartech.pgmc.mgu.entities.ParametroSistema;
import br.com.cleartech.pgmc.mgu.repositories.ParametroSistemaRepository;
import br.com.cleartech.pgmc.mgu.services.ParametroSistemaService;

@Service
public class ParametroSistemaServiceImpl implements ParametroSistemaService {

	@Autowired
	private ParametroSistemaRepository parametroSistemaRepository;

	@Override
	public ParametroSistema findByGrupoPrestadoraId( Long idGrupoPrestadora ) {
		return parametroSistemaRepository.findByGrupoPrestadoraId( idGrupoPrestadora );
	}

}
