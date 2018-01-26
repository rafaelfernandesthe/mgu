package br.com.cleartech.pgmc.mgu.repositories.impl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.BooleanBuilder;

import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.entities.QPrestadora;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.repositories.PrestadoraRepository;
import br.com.cleartech.pgmc.mgu.repositories.UsuarioRepository;
import br.com.cleartech.pgmc.mgu.repositories.util.QuerydslJpaRepositoryAux;

@Repository
@Transactional( readOnly = true )
public class PrestadoraRepositoryImpl extends QuerydslJpaRepositoryAux<Prestadora, Long> implements PrestadoraRepository {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public PrestadoraRepositoryImpl( EntityManager entityManager ) {
		super( Prestadora.class, entityManager );
	}

	QPrestadora qPrestadora = QPrestadora.prestadora;

	@Override
	public Prestadora prestadoraPorUsername( String dcUsername ) {
		BooleanBuilder bb = new BooleanBuilder();
		Usuario usuario = usuarioRepository.findByUsername( dcUsername );
		if ( usuario.getFlMaster() ) {
			bb.and( qPrestadora.prestadoraXUsuarios.any().usuario.dcUsername.eq( dcUsername ) );
		} else {
			bb.and( qPrestadora.delegados.any().usuarioComum.dcUsername.eq( dcUsername ) );
		}

		return createQuery( bb ).limit( 1 ).uniqueResult( qPrestadora );
	}

}
