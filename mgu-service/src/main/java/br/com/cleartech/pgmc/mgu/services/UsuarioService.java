package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;

public interface UsuarioService {

	Usuario salvar( Usuario usuario );
	
	Usuario findByUsername( String username );

	boolean existsByUsername( String username );

	Usuario findUsuarioMasterByUsernameAndIdPrestadora( String username, Long idPrestadora );

	void salvarUsuarioMaster( Usuario usuario ) throws Exception;

	void substituirUsuarioMaster( Usuario usuarioNovo, String usernameAnterior ) throws Exception;

	void bloquear( Usuario usuario, boolean removerMaster ) throws Exception;

	void alteraBloqueioUsuario( Usuario usuario, BloqueioUsuario bloqueio, String usuarioAlterando ) throws Exception;
}
