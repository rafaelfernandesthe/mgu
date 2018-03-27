package br.com.cleartech.pgmc.mgu.services;

import java.util.Calendar;
import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.FiltroValor;

public interface TempoRespostaService {

	boolean logger( Integer sistemaId, Integer funcionalidadeId, Calendar inicio, Calendar fim, List<FiltroValor> filtrosValores );

}
