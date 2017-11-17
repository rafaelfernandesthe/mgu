package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.repositories.PerfilRepositoryCustom;
import br.com.cleartech.pgmc.mgu.services.PerfilService;

@Component
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	PerfilRepositoryCustom perfilRepositoryCustom;

	@Override
	public List<Perfil> findByPrestadora( Long idPrestadora ) {
		return perfilRepositoryCustom.findByPrestadora( idPrestadora );
	}

}
