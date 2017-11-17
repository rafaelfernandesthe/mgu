package br.com.cleartech.pgmc.mgu.repositories;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import br.com.cleartech.pgmc.mgu.entities.Perfil;

@NoRepositoryBean
public interface PerfilRepositoryCustom extends Repository<Perfil, Long> {

	public List<Perfil> findByPrestadora( Long idPrestadora );

}
