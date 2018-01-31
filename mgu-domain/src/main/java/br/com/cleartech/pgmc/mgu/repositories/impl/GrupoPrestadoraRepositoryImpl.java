package br.com.cleartech.pgmc.mgu.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.ConstructorExpression;
import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;

import br.com.cleartech.pgmc.mgu.entities.GrupoPrestadora;
import br.com.cleartech.pgmc.mgu.entities.QGrupoPrestadora;
import br.com.cleartech.pgmc.mgu.repositories.GrupoPrestadoraRepository;
import br.com.cleartech.pgmc.mgu.repositories.util.QuerydslJpaRepositoryAux;

@Repository
@Transactional( readOnly = true )
public class GrupoPrestadoraRepositoryImpl extends QuerydslJpaRepositoryAux<GrupoPrestadora, Long> implements GrupoPrestadoraRepository {

	private static final QGrupoPrestadora qGrupoPrestadora = QGrupoPrestadora.grupoPrestadora;

	public GrupoPrestadoraRepositoryImpl( EntityManager entityManager ) {
		super( GrupoPrestadora.class, entityManager );
	}

	@Override
	public List<GrupoPrestadora> findAllByOrderByNoGrupoPrestadoraAsc() {
		JPQLQuery query = new JPAQuery( getEm() );
		query.from( qGrupoPrestadora ).orderBy( new OrderSpecifier<String>( Order.ASC, qGrupoPrestadora.noGrupoPrestadora ) );
		return query.list( ConstructorExpression.create( GrupoPrestadora.class, qGrupoPrestadora.id, qGrupoPrestadora.noGrupoPrestadora ) );
	}

}
