package br.com.cleartech.pgmc.mgu.services;

import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.Usuario;

public interface UsuarioService {

	Usuario salvar( Usuario usuario );
	
	Usuario findByUsername( String username );
	
	boolean existsByUsername( String username );

	Usuario salvarUsuarioMaster( Usuario usuario ) throws Exception;
	
	List<Usuario> findByPrestadorasId(Long id );
}
