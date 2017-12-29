package br.com.cleartech.pgmc.mgu.view.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;

public class GrupoPerfilConsultaDTO extends DTOSearchBase {

	private static final long serialVersionUID = -2539799703471445183L;

	private Long id;

	private String noGrupoPerfil;

	@Override
	public List<DTOSearchBase> getVO( List<?> list ) {
		List<DTOSearchBase> result = new ArrayList<>();
		if ( list != null )
			for ( GrupoPerfil item : (List<GrupoPerfil>) list ) {
				result.add( new GrupoPerfilConsultaDTO( item.getId(), item.getNoGrupoPerfil() ) );
			}
		return result;
	}

	public GrupoPerfilConsultaDTO() {

	}

	public GrupoPerfilConsultaDTO( Long id, String noGrupoPerfil ) {
		this.id = id;
		this.noGrupoPerfil = noGrupoPerfil;
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

}