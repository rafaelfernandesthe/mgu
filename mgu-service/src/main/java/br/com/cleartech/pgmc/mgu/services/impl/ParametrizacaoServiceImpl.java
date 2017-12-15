package br.com.cleartech.pgmc.mgu.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleartech.pgmc.mgu.entities.Parametrizacao;
import br.com.cleartech.pgmc.mgu.enums.TipoOperadora;
import br.com.cleartech.pgmc.mgu.repositories.ParametrizacaoRepository;
import br.com.cleartech.pgmc.mgu.services.ParametrizacaoService;

@Service
public class ParametrizacaoServiceImpl implements ParametrizacaoService {

	@Autowired
	private ParametrizacaoRepository parametrizacaoRepository;

	private Map<String, Parametrizacao> parametros = new HashMap<String, Parametrizacao>();

	@PostConstruct
	public void init() {
		if ( parametros.isEmpty() ) {
			List<Parametrizacao> listaParametros = (List<Parametrizacao>) parametrizacaoRepository.findAll();
			for ( Parametrizacao parametro : listaParametros ) {
				parametros.put( parametro.getDcParametro(), parametro );
			}
		}
	}

	@Override
	public String findByDcParametro( String dcParametro ) {
		if ( parametros.get( dcParametro ) != null )
			return parametros.get( dcParametro ).getVlParametro();
		return null;
	}

	@Override
	public TipoOperadora retornaTipoOperadora( Long idOperadora ) {

		String[] operadora = parametros.get( "OPER_PMS" ).getVlParametro().split( "," );
		for ( String ite : operadora ) {
			if ( ite.equals( String.valueOf( idOperadora ) ) ) {
				return TipoOperadora.PMS;
			}
		}

		operadora = parametros.get( "OPER_ESOA" ).getVlParametro().split( "," );
		for ( String ite : operadora ) {
			if ( ite.equals( String.valueOf( idOperadora ) ) ) {
				return TipoOperadora.ESOA;
			}
		}

		operadora = parametros.get( "OPER_ANATEL" ).getVlParametro().split( "," );
		for ( String ite : operadora ) {
			if ( ite.equals( String.valueOf( idOperadora ) ) ) {
				return TipoOperadora.ANATEL;
			}
		}
		return TipoOperadora.NAO_PMS;
	}

}
