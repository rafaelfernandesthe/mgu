package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleartech.pgmc.mgu.entities.AcessoSimultaneo;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.repositories.AcessoSimultaneoRepository;
import br.com.cleartech.pgmc.mgu.services.AcessoSimultaneoService;

@Service
@Transactional
public class AcessoSimultaneoServiceImpl implements AcessoSimultaneoService {

	@Autowired
	private AcessoSimultaneoRepository acessoSimultaneoRepository;

	@Override
	public boolean existsByUsername( String username ) {
		List<AcessoSimultaneo> acessosSimultaneos = acessoSimultaneoRepository.findByUsername( username );
		return acessosSimultaneos.isEmpty() ? false : true;
	}

	@Override
	public AcessoSimultaneo salvarByUsuario( Usuario usuario ) {
		AcessoSimultaneo acessoSimultaneo = new AcessoSimultaneo();
		acessoSimultaneo.setSistema( usuario.getSistema() );
		acessoSimultaneo.setUsername( usuario.getDcUsername() );
		acessoSimultaneo.setDtAcesso( Calendar.getInstance().getTime() );
		return acessoSimultaneoRepository.save( acessoSimultaneo );
	}


	@Override
	public void deletarByUsername( String username ) {
		List<AcessoSimultaneo> acessosSimultaneos = acessoSimultaneoRepository.findByUsername( username );
		if ( !acessosSimultaneos.isEmpty() ) {
			acessoSimultaneoRepository.delete( acessosSimultaneos );
		}
	}

}
