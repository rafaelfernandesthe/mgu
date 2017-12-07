package br.com.cleartech.pgmc.mgu.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.QPerfil;
import br.com.cleartech.pgmc.mgu.entities.Sistema;
import br.com.cleartech.pgmc.mgu.repositories.PerfilRepositoryCustom;
import br.com.cleartech.pgmc.mgu.repositories.util.QuerydslJpaRepositoryAux;

import com.mysema.query.BooleanBuilder;

@Repository
@Transactional( readOnly = true )
public class PerfilRepositoryImpl extends QuerydslJpaRepositoryAux<Perfil, Long> implements PerfilRepositoryCustom {

	public PerfilRepositoryImpl( EntityManager entityManager ) {
		super( Perfil.class, entityManager );
	}

	QPerfil qPerfil = QPerfil.perfil;

	@Override
	public List<Perfil> findByPrestadora( Long idPrestadora ) {
		BooleanBuilder bb = new BooleanBuilder();
		//TODO
//		bb.and( qPerfil.prestadora.id.eq( idPrestadora ) );
		return findAll( bb );

	}

	@Override
	public List<Perfil> findBySistema( Sistema sistema ) {
		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qPerfil.sistema.dcSistema.eq( sistema.getDcSistema() ) );
		return findAll( bb );
	}

}
