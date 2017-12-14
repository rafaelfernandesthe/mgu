package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleartech.pgmc.mgu.entities.AcessoSimultaneo;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.repositories.AcessoSimultaneoRepository;
import br.com.cleartech.pgmc.mgu.services.AcessoSimultaneoService;

@Service
public class AcessoSimultaneoServiceImpl implements AcessoSimultaneoService {

	@Autowired
	private AcessoSimultaneoRepository acessoSimultaneoRepository;

	@Override
	public boolean existsByUsername( String username ) {
		AcessoSimultaneo acessoSimultaneo = acessoSimultaneoRepository.findByUsername( username );
		return acessoSimultaneo != null ? true : false;
	}

	@Override
	public AcessoSimultaneo salvarByUsuario( Usuario usuario ) {
		AcessoSimultaneo acessoSimultaneo = new AcessoSimultaneo();
		acessoSimultaneo.setSistema( usuario.getSistema() );
		acessoSimultaneo.setUsername( usuario.getDcUsername() );
		acessoSimultaneo.setDtAcesso( Calendar.getInstance().getTime() );
		return acessoSimultaneoRepository.save( acessoSimultaneo );
	}

}
