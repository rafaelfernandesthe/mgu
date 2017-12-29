package br.com.cleartech.pgmc.mgu.services;

import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;

public interface UsuarioService {

	Usuario salvar( Usuario usuario );

	Usuario findByUsername( String username );

	List<Usuario> find( Usuario usuario );

	Usuario find( Long idUsuario );

	boolean existsByUsernameIgnoreCase( String username );

	Usuario findUsuarioMasterByUsernameAndIdPrestadora( String username, Long idPrestadora );

	void substituirUsuarioMaster( Usuario usuarioNovo, String usernameAnterior ) throws Exception;

	void bloquear( Usuario usuario, boolean removerMaster ) throws Exception;

	void alteraBloqueioUsuario( Usuario usuario, BloqueioUsuario bloqueio, String usuarioAlterando ) throws Exception;

	Usuario salvar( Usuario usuario, boolean master ) throws Exception;

	Usuario salvarEditar( Usuario usuarioAtualizado, Usuario usuarioDB ) throws Exception;
}
