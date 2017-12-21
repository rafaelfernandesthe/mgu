package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.entities.AcessoSimultaneo;
import br.com.cleartech.pgmc.mgu.entities.Usuario;

public interface AcessoSimultaneoService {

	boolean existsByUsername( String username );

	AcessoSimultaneo salvarByUsuario( Usuario usuario );

	void deletarByUsername( String username );
}
