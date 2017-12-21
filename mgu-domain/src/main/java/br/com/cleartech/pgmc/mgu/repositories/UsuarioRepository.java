package br.com.cleartech.pgmc.mgu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.cleartech.pgmc.mgu.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Usuario findByDcUsername(String username);

	Usuario findByDcUsernameIgnoreCase(String username);

	List<Usuario> findByPrestadorasId(Long id);

}
