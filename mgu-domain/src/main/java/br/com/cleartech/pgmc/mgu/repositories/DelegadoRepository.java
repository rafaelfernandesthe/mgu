package br.com.cleartech.pgmc.mgu.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.cleartech.pgmc.mgu.entities.Delegado;

public interface DelegadoRepository extends CrudRepository<Delegado, Long> {

	Delegado findByUsuarioComumDcUsernameAndPrestadoraId( String comumUsername, Long idPrestadora );

	Delegado findByUsuarioComumDcUsername( String comumUsername );

}
