package br.com.cleartech.pgmc.mgu.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;

@NoRepositoryBean
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Usuario findByUsername( String username );

	Usuario findByUsernameIgnoreCase( String username );

	Usuario findUsuarioMasterByUsernameAndIdPrestadora( String username, Long idPrestadora );

}
