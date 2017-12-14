package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.dtos.EmailDTO;

public interface EmailService {

	void sendEmail( EmailDTO email );
}
