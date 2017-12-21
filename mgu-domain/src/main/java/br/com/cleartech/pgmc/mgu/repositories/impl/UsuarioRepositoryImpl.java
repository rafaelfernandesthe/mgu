package br.com.cleartech.pgmc.mgu.repositories.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cleartech.pgmc.mgu.entities.QUsuario;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.repositories.UsuarioRepository;
import br.com.cleartech.pgmc.mgu.repositories.util.QuerydslJpaRepositoryAux;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.ConstructorExpression;

@Repository
@Transactional(readOnly = true)
public class UsuarioRepositoryImpl extends QuerydslJpaRepositoryAux<Usuario, Long> implements UsuarioRepository {

	public UsuarioRepositoryImpl(EntityManager entityManager) {
		super(Usuario.class, entityManager);
	}

	QUsuario qUsuario = QUsuario.usuario;

	@Override
	public Usuario findByDcUsername(String username) {
		BooleanBuilder bb = new BooleanBuilder();
		bb.and(qUsuario.dcUsername.eq(username));
		return createQuery(bb).uniqueResult(qUsuario);
	}

	@Override
	public Usuario findByDcUsernameIgnoreCase(String username) {
		BooleanBuilder bb = new BooleanBuilder();
		bb.and(qUsuario.dcUsername.equalsIgnoreCase(username));
		return createQuery(bb).uniqueResult(qUsuario);
	}

	@Override
	public List<Usuario> findByPrestadorasId(Long id) {
		BooleanBuilder bb = new BooleanBuilder();
		bb.and(qUsuario.prestadoras.any().id.eq(id));
		return createQuery(bb).list(
				ConstructorExpression.create(Usuario.class, qUsuario.nmUsuario, qUsuario.dcUsername, qUsuario.dcEmail, qUsuario.dcCargo, qUsuario.dcTelefone, qUsuario.nuCpf));
	}
}
