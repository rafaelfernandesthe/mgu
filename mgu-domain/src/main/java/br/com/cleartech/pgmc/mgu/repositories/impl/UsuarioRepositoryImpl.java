package br.com.cleartech.pgmc.mgu.repositories.impl;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import br.com.cleartech.pgmc.mgu.entities.QPrestadora;
import br.com.cleartech.pgmc.mgu.entities.QUsuario;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.BloqueioUsuario;
import br.com.cleartech.pgmc.mgu.repositories.UsuarioRepository;
import br.com.cleartech.pgmc.mgu.repositories.util.QuerydslJpaRepositoryAux;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

@Repository
public class UsuarioRepositoryImpl extends QuerydslJpaRepositoryAux<Usuario, Long> implements UsuarioRepository {

	private static final QUsuario qUsuario = QUsuario.usuario;
	private static final QPrestadora qPrestadora = QPrestadora.prestadora;

	public UsuarioRepositoryImpl( EntityManager entityManager ) {
		super( Usuario.class, entityManager );
	}

	@Override
	public Usuario findByUsername( String username ) {
		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qUsuario.dcUsername.eq( username ) );
		return createQuery( bb ).uniqueResult( qUsuario );
	}

	@Override
	public Usuario findByUsernameIgnoreCase( String username ) {
		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qUsuario.dcUsername.equalsIgnoreCase( username ) );
		return createQuery( bb ).uniqueResult( qUsuario );
	}

	@Override
	public Usuario findUsuarioMasterByUsernameAndIdPrestadora( String username, Long idPrestadora ) {
		JPAQuery query = new JPAQuery( getEm() );
		query.from( qUsuario ).innerJoin( qUsuario.prestadoras, qPrestadora );
		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qUsuario.dcUsername.eq( username ) );
		bb.and( qPrestadora.id.eq( idPrestadora ) );
		bb.and( qUsuario.flMaster.isTrue() );
		return query.where( bb ).uniqueResult( qUsuario );
	}
}
