package br.com.cleartech.pgmc.mgu.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.repositories.UsuarioRepository;
import br.com.cleartech.pgmc.mgu.services.UsuarioService;

@Component
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public Usuario salvar( Usuario usuario ) {
		return usuarioRepository.save( usuario );
	}

}
