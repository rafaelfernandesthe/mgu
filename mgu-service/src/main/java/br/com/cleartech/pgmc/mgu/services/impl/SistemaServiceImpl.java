package br.com.cleartech.pgmc.mgu.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleartech.pgmc.mgu.entities.Sistema;
import br.com.cleartech.pgmc.mgu.repositories.SistemaRepository;
import br.com.cleartech.pgmc.mgu.services.SistemaService;

@Service
public class SistemaServiceImpl implements SistemaService {

	@Autowired
	private SistemaRepository sistemaRepository;

	@Override
	public Sistema findByDcSistemaIgnoreCase( String sistema ) {
		return sistemaRepository.findByDcSistemaIgnoreCase( sistema );
	}
}
