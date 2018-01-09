package br.com.cleartech.pgmc.mgu.services;

import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;

public interface GrupoPerfilService {

	public GrupoPerfil salvar( GrupoPerfil grupoPerfil );

	public List<GrupoPerfil> findAll();

	public List<GrupoPerfil> findByPrestadora( Long idPrestadora );

	public List<GrupoPerfil> findByPrestadoraAndNome( GrupoPerfil grupoPerfil );

	public List<GrupoPerfil> loadAllById( List<GrupoPerfil> list );

	public List<GrupoPerfil> findByUsuario( Long idUsuario );

	public boolean existsByNoGrupoPerfil( String noGrupoPerfil );

	public void excluir( Long idGrupoPerfil );

	public GrupoPerfil find( Long idGrupoPerfil );

	public GrupoPerfil salvarEditar( GrupoPerfil grupoPerfil, GrupoPerfil grupoPerfilDB );

}
