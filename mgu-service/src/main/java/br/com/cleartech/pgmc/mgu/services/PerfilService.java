package br.com.cleartech.pgmc.mgu.services;

import java.util.List;

import br.com.cleartech.pgmc.mgu.dtos.PerfilDTO;
import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.Sistema;
import br.com.cleartech.pgmc.mgu.enums.TipoOperadora;

public interface PerfilService {

	public List<Perfil> findByPrestadora( Long idPrestadora );

	public List<Perfil> findBySistema( Sistema sistema );

	public List<PerfilDTO> findByUsernameAndSistema( String dcUsername, String sistema );

	boolean checkIsPerfilABRTOrCTECH( String perfil );

	public List<PerfilDTO> findPerfisMasterByUsernameAndSistema( String username, String sistema );

	public Perfil findByDcPerfilAndSistemaDcSistema( String perfil, String sistema );

	Perfil criarPerfil( String nomePerfil, Sistema sistema );

	Perfil criarPerfiOperadora( String nomePerfil, Sistema sistema, List<TipoOperadora> tipoOperadoras );

	boolean deletarPerfil( String nomePerfil, String sistema );

}
