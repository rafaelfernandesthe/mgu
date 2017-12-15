package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleartech.pgmc.mgu.entities.NivelEscalonamento;
import br.com.cleartech.pgmc.mgu.repositories.NivelEscalonamentoRepository;
import br.com.cleartech.pgmc.mgu.services.NivelEscalonamentoService;

@Service
public class NivelEscalonamentoServiceImpl implements NivelEscalonamentoService {

	@Autowired
	NivelEscalonamentoRepository repository;

	@Override
	public List<NivelEscalonamento> findAll() {
		return (List<NivelEscalonamento>) repository.findAll();
	}

	@Override
	public NivelEscalonamento findOne( Long id ) {
		return repository.findOne( id );
	}

}
