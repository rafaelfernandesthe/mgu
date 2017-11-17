package br.com.cleartech.pgmc.mgu.vo;

import java.util.ArrayList;
import java.util.List;

import br.com.cleartech.pgmc.mgu.entities.GrupoPerfil;
import br.com.cleartech.pgmc.mgu.utils.EntityVOBase;

public class GrupoPerfilConsultaVO extends EntityVOBase {

	private static final long serialVersionUID = -2539799703471445183L;

	private Long id;

	private String noGrupoPerfil;

	@Override
	public List<EntityVOBase> getVO( List<?> list ) {
		List<EntityVOBase> result = new ArrayList<>();
		if ( list != null )
			for ( GrupoPerfil item : (List<GrupoPerfil>) list ) {
				result.add( new GrupoPerfilConsultaVO( item.getId(), item.getNoGrupoPerfil() ) );
			}
		return result;
	}

	public GrupoPerfilConsultaVO() {

	}

	public GrupoPerfilConsultaVO( Long id, String noGrupoPerfil ) {
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