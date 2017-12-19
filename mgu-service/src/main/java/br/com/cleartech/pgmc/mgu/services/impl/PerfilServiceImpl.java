package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleartech.pgmc.mgu.dtos.PerfilDTO;
import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.PerfilAcessoOperadora;
import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.entities.Sistema;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.TipoOperadora;
import br.com.cleartech.pgmc.mgu.repositories.PerfilRepository;
import br.com.cleartech.pgmc.mgu.repositories.UsuarioRepository;
import br.com.cleartech.pgmc.mgu.services.ParametrizacaoService;
import br.com.cleartech.pgmc.mgu.services.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ParametrizacaoService parametrizacaoService;

	@Override
	public List<Perfil> findByPrestadora( Long idPrestadora ) {
		return perfilRepository.findByPrestadora( idPrestadora );
	}

	@Override
	public List<Perfil> findBySistema( Sistema sistema ) {
		return perfilRepository.findBySistema( sistema );
	}

	@Override
	public List<PerfilDTO> findByUsernameAndSistema( String username, String sistema ) {
		return perfilRepository.findByUsernameAndSistema( username, sistema );
	}

	@Override
	public boolean checkIsPerfilABRTOrCTECH( String perfil ) {
		return perfilRepository.checkIsPerfilABRTOrCTECH( perfil );
	}

	@Override
	public List<PerfilDTO> findPerfisMasterByUsernameAndSistema( String username, String sistema ) {
		List<Perfil> perfis = perfilRepository.findPerfisMasterByUsernameAndSistema( username, sistema );
		Usuario usuario = usuarioRepository.findByUsername( username );

		Set<PerfilDTO> perfisDTO = new HashSet<PerfilDTO>();
		for ( Prestadora prestadora : usuario.getPrestadoras() ) {
			TipoOperadora tipo = parametrizacaoService.retornaTipoOperadora( prestadora.getId() );
			for ( Perfil perfil : perfis ) {
				List<PerfilAcessoOperadora> perfilAcessoOperadoras = perfil.getPerfilAcessoOperadoras();
				for ( PerfilAcessoOperadora perfilAcesso : perfilAcessoOperadoras ) {
					if ( perfilAcesso.getAcessoOperadora().getTipoOperadora().equals( tipo ) ) {
						perfisDTO.add( new PerfilDTO( perfil.getId(), perfil.getDcPerfil(), prestadora.getId(), prestadora.getNoPrestadora() ) );
					}
				}
			}
		}

		return new ArrayList<PerfilDTO>( perfisDTO );
	}

	@Override
	public Perfil findByDcPerfilAndSistemaDcSistema( String perfil, String sistema ) {
		return perfilRepository.findByDcPerfilAndSistemaDcSistema( perfil, sistema );
	}

}
