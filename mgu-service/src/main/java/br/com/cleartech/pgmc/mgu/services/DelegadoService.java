package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.entities.Delegado;
import br.com.cleartech.pgmc.mgu.entities.Usuario;

public interface DelegadoService {

	Delegado findByUsuarioComumDcUsernameAndPrestadoraId( String comumUsername, Long idPrestadora );

	Delegado findByUsuarioComumDcUsername( String comumUsername );

	void removerDelegadoDeUsuarioMaster( Usuario usuarioMaster );

	void salvar( Delegado delegado );

}
