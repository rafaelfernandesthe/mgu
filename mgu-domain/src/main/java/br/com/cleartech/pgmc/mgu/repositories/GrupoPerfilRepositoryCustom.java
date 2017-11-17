package br.com.cleartech.pgmc.mgu.repositories;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;

@NoRepositoryBean
public interface GrupoPerfilRepositoryCustom extends Repository<GrupoPerfil, Long> {

	public List<GrupoPerfil> findByPrestadora( Long idPrestadora );

	public List<GrupoPerfil> findByPrestadoraAndNome( Long idPrestadora, String noGrupoPerfil );
}
