package br.com.cleartech.pgmc.mgu.services;

import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.NivelEscalonamento;

public interface NivelEscalonamentoService {

	public NivelEscalonamento findOne( Long id );

	public List<NivelEscalonamento> findAll();

}
