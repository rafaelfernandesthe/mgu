package br.com.cleartech.pgmc.mgu.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.ConstructorExpression;

import br.com.cleartech.pgmc.mgu.entities.QPrestadora;
import br.com.cleartech.pgmc.mgu.entities.QUsuario;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.repositories.UsuarioRepository;
import br.com.cleartech.pgmc.mgu.repositories.util.QuerydslJpaRepositoryAux;
import br.com.cleartech.pgmc.mgu.utils.QueryUtils;
import br.com.cleartech.pgmc.mgu.utils.StringUtils;

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

	@Override
	public List<Usuario> find( Usuario usuario ) {
		BooleanBuilder bb = new BooleanBuilder();
		if ( !StringUtils.isEmpty( usuario.getNmUsuario() ) ) {
			bb.and( QueryUtils.containsIgnoreCaseIgnoreAccents( qUsuario.nmUsuario, usuario.getNmUsuario() ) );
		}
		if ( !StringUtils.isEmpty( usuario.getDcUsername() ) ) {
			bb.and( QueryUtils.containsIgnoreCaseIgnoreAccents( qUsuario.dcUsername, usuario.getDcUsername() ) );
		}
		if ( !StringUtils.isEmpty( usuario.getDcEmail() ) ) {
			bb.and( qUsuario.dcEmail.eq( usuario.getDcEmail() ) );
		}
		if ( !StringUtils.isEmpty( usuario.getDcCargo() ) ) {
			bb.and( QueryUtils.containsIgnoreCaseIgnoreAccents( qUsuario.dcCargo, usuario.getDcCargo() ) );
		}
		if ( !StringUtils.isEmpty( usuario.getDcTelefone() ) ) {
			bb.and( qUsuario.dcTelefone.eq( usuario.getDcTelefone() ) );
		}
		if ( !StringUtils.isEmpty( usuario.getNuCpf() ) ) {
			bb.and( qUsuario.nuCpf.eq( usuario.getNuCpf() ) );
		}
		if ( !usuario.getGrupoPerfis().isEmpty() ) {
			bb.and( qUsuario.grupoPerfis.any().id.eq( usuario.getGrupoPerfis().get( 0 ).getId() ) );
		}
		if ( usuario.getFlBloqueio() != null ) {
			bb.and( qUsuario.flBloqueio.eq( usuario.getFlBloqueio() ) );
		}

		return createQuery( bb ).list( ConstructorExpression.create( Usuario.class, qUsuario.id, qUsuario.nmUsuario, qUsuario.dcUsername, qUsuario.dcEmail, qUsuario.dcCargo, qUsuario.dcTelefone, qUsuario.nuCpf, qUsuario.flBloqueio ) );
	}
}
