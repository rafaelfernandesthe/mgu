package br.com.cleartech.pgmc.mgu.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.com.cleartech.pgmc.mgu.entities.PrestadoraPMSMercado;
import br.com.cleartech.pgmc.mgu.entities.QPrestadoraPMSMercado;
import br.com.cleartech.pgmc.mgu.repositories.PrestadoraPMSMercadoRepository;
import br.com.cleartech.pgmc.mgu.repositories.util.QuerydslJpaRepositoryAux;

import com.mysema.query.BooleanBuilder;

@Repository
public class PrestadoraPMSMercadoRepositoryImpl extends QuerydslJpaRepositoryAux<PrestadoraPMSMercado, Long> implements PrestadoraPMSMercadoRepository {

	private static final QPrestadoraPMSMercado qPrestadoraPMSMercado = QPrestadoraPMSMercado.prestadoraPMSMercado;

	public PrestadoraPMSMercadoRepositoryImpl( EntityManager entityManager ) {
		super( PrestadoraPMSMercado.class, entityManager );
	}

	@Override
	public boolean checkIsPrestadoraPMS( List<Long> idPrestadoras ) {
		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qPrestadoraPMSMercado.idPrestadora.in( idPrestadoras ) );
		return createQuery( bb ).exists();
	}

}
