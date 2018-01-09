package br.com.cleartech.pgmc.mgu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import br.com.cleartech.pgmc.mgu.dtos.PerfilDTO;
import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.Sistema;
import br.com.cleartech.pgmc.mgu.enums.TipoOperadora;

@NoRepositoryBean
public interface PerfilRepository extends CrudRepository<Perfil, Long> {

	public List<Perfil> findByPrestadora( Long idPrestadora, String usernameUsuarioLogado, TipoOperadora tipoOperadora );

	public List<Perfil> findBySistema( Sistema sistema );

	public List<PerfilDTO> findByUsernameAndSistema( String username, String sistema );

	boolean checkIsPerfilABRTOrCTECH( String perfil );

	public List<Perfil> findPerfisMasterByUsernameAndSistema( String username, String sistema );

	public Perfil findByDcPerfilAndSistema( String perfil, String sistema );

	public List<Perfil> findByGrupoPerfil( Long grupoPerfilId );

}
