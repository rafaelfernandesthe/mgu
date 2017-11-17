package br.com.cleartech.pgmc.mgu.services;

import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;

public interface GrupoPerfilService {

	public GrupoPerfil salvar( GrupoPerfil grupoPerfil );

	public List<GrupoPerfil> findAll();

	public List<GrupoPerfil> findByPrestadora( Long idPrestadora );

	public List<GrupoPerfil> findByPrestadoraAndNome( GrupoPerfil grupoPerfil );

}
