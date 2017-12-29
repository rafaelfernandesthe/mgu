package br.com.cleartech.pgmc.mgu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;

@NoRepositoryBean
public interface GrupoPerfilRepository extends CrudRepository<GrupoPerfil, Long> {

	public List<GrupoPerfil> findByPrestadora( Long idPrestadora );

	public List<GrupoPerfil> findByPrestadoraAndNome( Long idPrestadora, String noGrupoPerfil );

	public List<GrupoPerfil> findByUsuario( Long idUsuario );
}
