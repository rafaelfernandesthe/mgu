package br.com.cleartech.pgmc.mgu.view.dtos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.Prestadora;

public class GrupoPerfilCadastroDTO implements Serializable {

	private static final long serialVersionUID = 5191694876259666892L;

	private Long id;

	private String noGrupoPerfil;

	@NotEmpty
	private List<Integer> perfisIdList;

	private List<Perfil> perfis;

	private Prestadora prestadora;

	public GrupoPerfilCadastroDTO() {}

	public GrupoPerfilCadastroDTO( GrupoPerfil grupoPerfil ) {
		this.setId( grupoPerfil.getId() );
		this.setNoGrupoPerfil( grupoPerfil.getNoGrupoPerfil() );
		this.setPerfis( grupoPerfil.getPerfis() );
		this.setPrestadora( grupoPerfil.getPrestadora() );
	}

	public GrupoPerfil getGrupoPerfil() {
		GrupoPerfil grupoPerfil = new GrupoPerfil();
		grupoPerfil.setId( this.getId() );
		grupoPerfil.setNoGrupoPerfil( this.getNoGrupoPerfil() );
		grupoPerfil.setPerfis( this.getPerfis() );
		grupoPerfil.setPrestadora( this.getPrestadora() );
		return grupoPerfil;
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getNoGrupoPerfil() {
		return noGrupoPerfil;
	}

	public void setNoGrupoPerfil( String noGrupoPerfil ) {
		this.noGrupoPerfil = noGrupoPerfil;
	}

	public List<Integer> getPerfisIdList() {
		return perfisIdList;
	}

	public void setPerfisIdList( List<Integer> perfisIdList ) {
		this.perfisIdList = perfisIdList;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis( List<Perfil> perfis ) {
		this.perfis = perfis;
	}

	public Prestadora getPrestadora() {
		return prestadora;
	}

	public void setPrestadora( Prestadora prestadora ) {
		this.prestadora = prestadora;
	}

}