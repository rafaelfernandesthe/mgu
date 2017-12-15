package br.com.cleartech.pgmc.mgu.services;

import javax.mail.MessagingException;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.AssuntoEnum;

public interface EmailService {

	void enviaByUsuarioAndAssunto( Usuario usuario, AssuntoEnum assunto ) throws MessagingException;
}
