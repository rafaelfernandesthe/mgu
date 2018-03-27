package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleartech.pgmc.mgu.entities.Filtro;
import br.com.cleartech.pgmc.mgu.entities.FiltroTempoResposta;
import br.com.cleartech.pgmc.mgu.entities.FiltroValor;
import br.com.cleartech.pgmc.mgu.entities.Funcionalidade;
import br.com.cleartech.pgmc.mgu.entities.SistemaTR;
import br.com.cleartech.pgmc.mgu.entities.TempoResposta;
import br.com.cleartech.pgmc.mgu.repositories.TempoRespostaRepository;
import br.com.cleartech.pgmc.mgu.services.TempoRespostaService;

@Service
@Transactional
public class TempoRespostaServiceImpl implements TempoRespostaService {

	@Autowired
	private TempoRespostaRepository tempoRespostaRepository;

	@Override
	public boolean logger( Integer sistemaId, Integer funcionalidadeId, Calendar inicio, Calendar fim, List<FiltroValor> filtrosValores ) {

		SistemaTR sistema = new SistemaTR( sistemaId );
		Funcionalidade funcionalidade = new Funcionalidade( funcionalidadeId );

		// Sistema
		sistema.setId( sistemaId );
		sistema.addFuncionalidade( funcionalidade );

		// Funcionalidade
		funcionalidade.setId( funcionalidadeId );
		funcionalidade.setSistema( sistema );

		// Tempo Resposta
		TempoResposta tempoResposta = new TempoResposta();
		tempoResposta.setFim( fim );
		tempoResposta.setInicio( inicio );
		tempoResposta.setFuncionalidade( funcionalidade );

		for ( FiltroValor filtroValor : filtrosValores ) {
			Filtro filtro = new Filtro();
			FiltroTempoResposta filtroTempoResposta = new FiltroTempoResposta();
			// Set Filtro id
			filtro.setId( filtroValor.getFiltroId() );
			// Set Filtro em Filtro Tempo Resposta
			filtroTempoResposta.setFiltro( filtro );
			// Set Tempo Resposta
			filtroTempoResposta.setTempoResposta( tempoResposta );
			// Set Valor do Filtro
			filtroTempoResposta.setValor( filtroValor.getValor() );
			// Add filtros tempo resposta
			tempoResposta.addFiltrosTempoResposta( filtroTempoResposta );
		}

		funcionalidade.addTempoRespostas( tempoResposta );
		tempoResposta.setFuncionalidade( funcionalidade );

		try {
			tempoRespostaRepository.save( tempoResposta );
		} catch ( Exception e ) {
			System.out.println( "ERROR - Ocorreu um erro ao tentar salvar o tempo resposta!" );
			e.printStackTrace();
		}

		return true;

	}
}
