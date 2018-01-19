package br.com.cleartech.pgmc.mgu.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleartech.pgmc.mgu.entities.ParametroSistema;
import br.com.cleartech.pgmc.mgu.repositories.ParametroSistemaRepository;
import br.com.cleartech.pgmc.mgu.services.ParametroSistemaService;

@Service
@Transactional
public class ParametroSistemaServiceImpl implements ParametroSistemaService {

	@Autowired
	private ParametroSistemaRepository parametroSistemaRepository;

	@Override
	public ParametroSistema salvar( ParametroSistema parametroSistema ) {
		return parametroSistemaRepository.save( parametroSistema );
	}

	@Override
	@Transactional( readOnly = true )
	public ParametroSistema findByGrupoPrestadoraId( Long idGrupoPrestadora ) {
		return parametroSistemaRepository.findByGrupoPrestadoraId( idGrupoPrestadora );
	}


}
