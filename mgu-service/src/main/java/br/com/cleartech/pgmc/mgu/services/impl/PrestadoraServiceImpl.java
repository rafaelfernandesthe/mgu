package br.com.cleartech.pgmc.mgu.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.repositories.PrestadoraRepository;
import br.com.cleartech.pgmc.mgu.services.PrestadoraService;

@Service
public class PrestadoraServiceImpl implements PrestadoraService {

	@Autowired
	private PrestadoraRepository prestadoraRepository;

	@Override
	public Prestadora findById( Long id ) {
		return prestadoraRepository.findOne( id );
	}

}
