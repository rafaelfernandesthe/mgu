package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleartech.pgmc.mgu.dtos.PerfilDTO;
import br.com.cleartech.pgmc.mgu.entities.AcessoOperadora;
import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.PerfilAcessoOperadora;
import br.com.cleartech.pgmc.mgu.entities.PrestadoraXUsuario;
import br.com.cleartech.pgmc.mgu.entities.Sistema;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.TipoOperadora;
import br.com.cleartech.pgmc.mgu.repositories.AcessoOperadoraRepository;
import br.com.cleartech.pgmc.mgu.repositories.PerfilRepository;
import br.com.cleartech.pgmc.mgu.repositories.UsuarioRepository;
import br.com.cleartech.pgmc.mgu.services.ParametrizacaoService;
import br.com.cleartech.pgmc.mgu.services.PerfilService;

@Service
@Transactional
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ParametrizacaoService parametrizacaoService;

	@Autowired
	private AcessoOperadoraRepository acessoOperadoraRepository;

	@Override
	public List<Perfil> findByPrestadora( Long idPrestadora, String usernameUsuarioLogado ) {
		return perfilRepository.findByPrestadora( idPrestadora, usernameUsuarioLogado, parametrizacaoService.retornaTipoOperadora( idPrestadora ) );
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
		for ( PrestadoraXUsuario prestadoraXusuario : usuario.getPrestadoraXUsuarios() ) {
			TipoOperadora tipo = parametrizacaoService.retornaTipoOperadora( prestadoraXusuario.getPrestadora().getId() );
			for ( Perfil perfil : perfis ) {
				List<PerfilAcessoOperadora> perfilAcessoOperadoras = perfil.getPerfilAcessoOperadoras();
				for ( PerfilAcessoOperadora perfilAcesso : perfilAcessoOperadoras ) {
					if ( perfilAcesso.getAcessoOperadora().getTipoOperadora().equals( tipo ) ) {
						perfisDTO.add( new PerfilDTO( perfil.getId(), perfil.getDcPerfil(), prestadoraXusuario.getPrestadora().getId(), prestadoraXusuario.getPrestadora().getNoPrestadora() ) );
					}
				}
			}
		}

		return new ArrayList<PerfilDTO>( perfisDTO );
	}

	@Override
	public Perfil findByDcPerfilAndSistemaDcSistema( String perfil, String sistema ) {
		return perfilRepository.findByDcPerfilAndSistema( perfil, sistema );
	}

	@Override
	public Perfil criarPerfil( String nomePerfil, Sistema sistema ) {
		Perfil perfil = new Perfil();
		perfil.setDcPerfil( nomePerfil );
		String descr = nomePerfil + " (Criado pelo sistema " + sistema.getDcSistema().trim() + ")";
		if ( descr.length() > 50 ) {
			descr = descr.substring( 0, 50 );
		}
		perfil.setDcDescricao( descr );
		perfil.setSistema( sistema );
		return perfilRepository.save( perfil );
	}

	@Override
	public Perfil criarPerfiOperadora( String nomePerfil, Sistema sistema, List<TipoOperadora> tipoOperadoras ) {
		Perfil perfil = criarPerfil( nomePerfil, sistema );

		for ( TipoOperadora tipoOperadora : tipoOperadoras ) {
			AcessoOperadora acessoOperadora = acessoOperadoraRepository.findByTipoOperadora( tipoOperadora );
			PerfilAcessoOperadora perfilAcessoOperadora = new PerfilAcessoOperadora();
			perfilAcessoOperadora.setPerfil( perfil );
			perfilAcessoOperadora.setAcessoOperadora( acessoOperadora );
			perfil.getPerfilAcessoOperadoras().add( perfilAcessoOperadora );
		}
		return perfilRepository.save( perfil );
	}

	@Override
	public boolean deletarPerfil( String nomePerfil, String sistema ) {
		Perfil perfil = perfilRepository.findByDcPerfilAndSistema( nomePerfil, sistema );
		if ( perfil != null ) {
			perfilRepository.delete( perfil );
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Perfil> loadAllById( List<Perfil> list ) {
		List<Perfil> result = new ArrayList<>();
		for ( Perfil item : list ) {
			result.add( perfilRepository.findOne( item.getId() ) );
		}
		return result;
	}

	@Override
	public List<Perfil> findByGrupoPerfil( Long grupoPerfilId ) {
		return perfilRepository.findByGrupoPerfil( grupoPerfilId );
	}
}
