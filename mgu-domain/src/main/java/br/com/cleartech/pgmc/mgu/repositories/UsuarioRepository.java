package br.com.cleartech.pgmc.mgu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.cleartech.pgmc.mgu.entities.Usuario;

@NoRepositoryBean
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Usuario findByUsername( String username );

	Usuario findByUsernameIgnoreCase( String username );

	Usuario findUsuarioMasterByUsernameAndIdPrestadora( String username, Long idPrestadora );

	List<Usuario> find( Usuario usuario, Long prestadoraId );

	List<Usuario> findUsuarioDelegadoDisponivel( Long idUsuario, Long idPrestadora );

}
