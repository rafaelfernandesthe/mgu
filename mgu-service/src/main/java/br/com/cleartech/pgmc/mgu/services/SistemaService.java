package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.entities.Sistema;

public interface SistemaService {

	Sistema findByDcSistemaIgnoreCase( String sistema );

}
