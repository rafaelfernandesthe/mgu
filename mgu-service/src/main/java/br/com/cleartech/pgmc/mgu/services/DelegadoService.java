package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.entities.Delegado;

public interface DelegadoService {

	Delegado findByUsuarioComumDcUsernameAndPrestadoraId( String comumUsername, Long idPrestadora );

	Delegado findByUsuarioComumDcUsername( String comumUsername );

}
