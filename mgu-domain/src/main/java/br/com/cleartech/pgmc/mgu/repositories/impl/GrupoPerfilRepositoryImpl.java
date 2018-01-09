package br.com.cleartech.pgmc.mgu.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.ConstructorExpression;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.QGrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.QUsuario;
import br.com.cleartech.pgmc.mgu.repositories.GrupoPerfilRepository;
import br.com.cleartech.pgmc.mgu.repositories.util.QuerydslJpaRepositoryAux;
import br.com.cleartech.pgmc.mgu.utils.QueryUtils;

@Repository
@Transactional( readOnly = true )
public class GrupoPerfilRepositoryImpl extends QuerydslJpaRepositoryAux<GrupoPerfil, Long> implements GrupoPerfilRepository {

	public GrupoPerfilRepositoryImpl( EntityManager entityManager ) {
		super( GrupoPerfil.class, entityManager );
	}

	QGrupoPerfil qGrupoPerfil = QGrupoPerfil.grupoPerfil;
	QUsuario qUsuario = QUsuario.usuario;

	@Override
	public List<GrupoPerfil> findByPrestadora( Long idPrestadora ) {
		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qGrupoPerfil.prestadora.id.eq( idPrestadora ) );
		return findAll( bb );
	}

	@Override
	public List<GrupoPerfil> findByPrestadoraAndNome( Long idPrestadora, String noGrupoPerfil ) {
		BooleanBuilder bb = new BooleanBuilder();
		if ( idPrestadora != null )
			bb.and( qGrupoPerfil.prestadora.id.eq( idPrestadora ) );
		if ( noGrupoPerfil != null )
			bb.and( QueryUtils.containsIgnoreCaseIgnoreAccents( qGrupoPerfil.noGrupoPerfil, noGrupoPerfil ) );

		return findAll( bb );
	}

	@Override
	public List<GrupoPerfil> findByUsuario( Long idUsuario ) {
		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qGrupoPerfil.usuarios.any().id.eq( idUsuario ) );
		return createQuery( bb ).list( ConstructorExpression.create( GrupoPerfil.class, qGrupoPerfil.id, qGrupoPerfil.noGrupoPerfil ) );
	}

	@Override
	public boolean existsByNoGrupoPerfil( String noGrupoPerfil ) {
		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qGrupoPerfil.noGrupoPerfil.eq( noGrupoPerfil ) );
		return createQuery( bb ).exists();
	}

}
