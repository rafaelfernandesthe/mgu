package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.repositories.GrupoPerfilRepository;
import br.com.cleartech.pgmc.mgu.repositories.GrupoPerfilRepository;
import br.com.cleartech.pgmc.mgu.services.GrupoPerfilService;

@Component
public class GrupoPerfilServiceImpl implements GrupoPerfilService {

	@Autowired
	private GrupoPerfilRepository grupoPerfilRepository;

	@Override
	public GrupoPerfil salvar( GrupoPerfil grupoPerfil ) {
		return grupoPerfilRepository.save( grupoPerfil );
	}

	@Override
	public List<GrupoPerfil> findAll() {
		return (List<GrupoPerfil>) grupoPerfilRepository.findAll();
	}

	@Override
	public List<GrupoPerfil> findByPrestadora( Long idPrestadora ) {
		return grupoPerfilRepository.findByPrestadora( idPrestadora );
	}

	@Override
	public List<GrupoPerfil> findByPrestadoraAndNome( GrupoPerfil grupoPerfil ) {
		return grupoPerfilRepository.findByPrestadoraAndNome( grupoPerfil.getPrestadora().getId(), grupoPerfil.getNoGrupoPerfil() );
	}

}
