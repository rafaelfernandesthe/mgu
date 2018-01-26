package br.com.cleartech.pgmc.mgu.repositories.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.ConstructorExpression;
import com.mysema.query.types.Order;
import com.mysema.query.types.OrderSpecifier;

import br.com.cleartech.pgmc.mgu.dtos.PerfilDTO;
import br.com.cleartech.pgmc.mgu.entities.Modulo;
import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.QGrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.QModulo;
import br.com.cleartech.pgmc.mgu.entities.QPerfil;
import br.com.cleartech.pgmc.mgu.entities.QPrestadora;
import br.com.cleartech.pgmc.mgu.entities.QUsuario;
import br.com.cleartech.pgmc.mgu.entities.Sistema;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.enums.TipoOperadora;
import br.com.cleartech.pgmc.mgu.repositories.PerfilRepository;
import br.com.cleartech.pgmc.mgu.repositories.UsuarioRepository;
import br.com.cleartech.pgmc.mgu.repositories.util.QuerydslJpaRepositoryAux;

@Repository
@Transactional( readOnly = true )
public class PerfilRepositoryImpl extends QuerydslJpaRepositoryAux<Perfil, Long> implements PerfilRepository {

	private static final QPerfil qPerfil = QPerfil.perfil;
	private static final QGrupoPerfil qGrupoPerfil = QGrupoPerfil.grupoPerfil;
	private static final QUsuario qUsuario = QUsuario.usuario;
	private static final QModulo qModulo = QModulo.modulo;
	private static final QPrestadora qPrestadora = QPrestadora.prestadora;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public PerfilRepositoryImpl( EntityManager entityManager ) {
		super( Perfil.class, entityManager );
	}

	@Override
	public List<Perfil> findByPrestadora( Long idPrestadora, String usernameUsuarioLogado, TipoOperadora tipoOperadora ) {
		JPAQuery q = new JPAQuery( getEm() );
		q.from( qModulo ).where( qModulo.prestadoras.any().prestadora.id.eq( idPrestadora ) );
		List<Modulo> modulos = q.list( qModulo );

		Set<String> listaModulo = new HashSet<String>();
		for ( Modulo m : modulos ) {
			listaModulo.add( m.getDcModulo() );
		}

		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qPerfil.perfilAcessoOperadoras.any().acessoOperadora.tipoOperadora.eq( tipoOperadora ) );

		Usuario dbUser = usuarioRepository.findByUsername( usernameUsuarioLogado );
		if ( !dbUser.getDcEmail().toUpperCase().contains( "@CLEARTECH" ) ) {
			bb.and( qPerfil.dcDescricao.notLike( "PERFIL SEM NOTIFICACOES" ) );
		}

		if ( !listaModulo.isEmpty() ) {
			bb.and( qPerfil.sistema.dcSistema.in( listaModulo ) );
		}

		JPQLQuery query = createQuery( bb ).orderBy( new OrderSpecifier<>( Order.ASC, qPerfil.dcPerfil ) );

		return query.list( ConstructorExpression.create( Perfil.class, qPerfil.id, qPerfil.dcPerfil, qPerfil.dcDescricao, qPerfil.sistema ) );

	}

	@Override
	public List<Perfil> findBySistema( Sistema sistema ) {
		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qPerfil.sistema.dcSistema.equalsIgnoreCase( sistema.getDcSistema() ) );
		// createQuery( bb ).orderBy( qPerfil.dcPerfil.asc() ).list();
		return createQuery( bb ).orderBy( qPerfil.dcPerfil.asc() ).list( qPerfil );
	}

	@Override
	public List<PerfilDTO> findByUsernameAndSistema( String username, String sistema ) {

		JPAQuery query = new JPAQuery( getEm() );
		query.from( qPerfil ).innerJoin( qPerfil.grupoPerfilXPerfils.any().grupoPerfil, qGrupoPerfil ).innerJoin( qGrupoPerfil.usuarioXGrupoPerfils.any().usuario, qUsuario ).innerJoin( qUsuario.prestadoraXUsuarios.any().prestadora, qPrestadora );

		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qPerfil.sistema.dcSistema.equalsIgnoreCase( sistema ) );
		bb.and( qUsuario.dcUsername.eq( username ) );

		// Clausula para limitar acesso por modulo do credenciamento
		JPASubQuery subQuery = new JPASubQuery();
		subQuery.from( qModulo ).innerJoin( qModulo.prestadoras.any().prestadora, qPrestadora ).innerJoin( qPrestadora.prestadoraXUsuarios.any().usuario, qUsuario );
		BooleanBuilder bbSub = new BooleanBuilder();
		bbSub.and( qUsuario.dcUsername.eq( username ) );

		bb.and( qPerfil.sistema.dcSistema.in( subQuery.where( bbSub ).distinct().list( qModulo.dcModulo ) ) );

		return query.where( bb ).orderBy( qPerfil.dcPerfil.asc() ).distinct().list( ConstructorExpression.create( PerfilDTO.class, qPerfil.id, qPerfil.dcPerfil, qGrupoPerfil.prestadora.id, qGrupoPerfil.prestadora.noPrestadora ) );
	}

	@Override
	public boolean checkIsPerfilABRTOrCTECH( String perfil ) {
		BooleanBuilder bb = new BooleanBuilder();
		JPAQuery query = new JPAQuery( getEm() );
		query.from( qPerfil ).innerJoin( qPerfil.grupoPerfilXPerfils.any().grupoPerfil, qGrupoPerfil );

		bb.andAnyOf( qPerfil.dcPerfil.endsWith( "_ABRT" ), qPerfil.dcPerfil.endsWith( "_CTECH" ) );
		bb.and( qPerfil.dcPerfil.eq( perfil ) );

		return query.where( bb ).exists();
	}

	@Override
	public List<Perfil> findPerfisMasterByUsernameAndSistema( String username, String sistema ) {
		JPAQuery query = new JPAQuery( getEm() ).from( qPerfil );

		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qPerfil.sistema.dcSistema.equalsIgnoreCase( sistema ) );

		// Clausula para limitar acesso por modulo do credenciamento
		JPASubQuery subQuery = new JPASubQuery();
		// subQuery.from( qModulo ).innerJoin( qModulo.prestadoras, qPrestadora
		// ).innerJoin( qPrestadora.usuarios, qUsuario );
		subQuery.from( qModulo ).innerJoin( qModulo.prestadoras.any().prestadora, qPrestadora ).innerJoin( qPrestadora.prestadoraXUsuarios.any().usuario, qUsuario );
		BooleanBuilder bbSub = new BooleanBuilder();
		bbSub.and( qUsuario.dcUsername.eq( username ) );

		bb.and( qPerfil.sistema.dcSistema.in( subQuery.where( bbSub ).distinct().list( qModulo.dcModulo ) ) );

		return query.where( bb ).orderBy( qPerfil.dcPerfil.asc() ).list( qPerfil );

	}

	@Override
	public Perfil findByDcPerfilAndSistema( String perfil, String sistema ) {
		JPAQuery query = new JPAQuery( getEm() ).from( qPerfil );

		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qPerfil.dcPerfil.equalsIgnoreCase( perfil ) );
		bb.and( qPerfil.sistema.dcSistema.equalsIgnoreCase( sistema ) );

		return query.where( bb ).uniqueResult( qPerfil );
	}

	@Override
	public List<Perfil> findByGrupoPerfil( Long grupoPerfilId ) {
		BooleanBuilder bb = new BooleanBuilder();
		bb.and( qPerfil.grupoPerfilXPerfils.any().grupoPerfil.id.eq( grupoPerfilId ) );
		return createQuery( bb ).distinct().list( ConstructorExpression.create( Perfil.class, qPerfil.id, qPerfil.dcPerfil, qPerfil.dcDescricao, qPerfil.sistema ) );
	}

}
