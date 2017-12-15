package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.AssuntoEnum;

public interface EmailService {

	void enviaByUsuarioAndAssunto( Usuario usuario, AssuntoEnum assunto );

}
