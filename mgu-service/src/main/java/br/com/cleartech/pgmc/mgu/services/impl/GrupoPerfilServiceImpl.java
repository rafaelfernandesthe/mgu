package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
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

	@Override
	public List<GrupoPerfil> loadAllById( List<GrupoPerfil> list ) {
		List<GrupoPerfil> result = new ArrayList<>();
		for ( GrupoPerfil item : list ) {
			result.add( grupoPerfilRepository.findOne( item.getId() ) );
		}
		return result;
	}

	@Override
	public List<GrupoPerfil> findByUsuario( Long idUsuario ) {
		return grupoPerfilRepository.findByUsuario( idUsuario );
	}

	@Override
	public boolean existsByNoGrupoPerfil( String noGrupoPerfil ) {
		return grupoPerfilRepository.existsByNoGrupoPerfil( noGrupoPerfil );
	}

	@Override
	public void excluir( Long idGrupoPerfil ) {
		grupoPerfilRepository.delete( idGrupoPerfil );
	}

	@Override
	public GrupoPerfil find( Long idGrupoPerfil ) {
		return grupoPerfilRepository.findOne( idGrupoPerfil );
	}

	@Override
	public GrupoPerfil salvarEditar( GrupoPerfil grupoPerfilAtualizado, GrupoPerfil grupoPerfilDB ) {
		grupoPerfilDB.setPerfis( grupoPerfilAtualizado.getPerfis() );
		grupoPerfilRepository.save( grupoPerfilDB );
		return grupoPerfilDB;
	}

}
