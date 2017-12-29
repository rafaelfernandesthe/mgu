package br.com.cleartech.pgmc.mgu.view.dtos;

import java.io.Serializable;
import java.util.List;

import br.com.cleartech.pgmc.mgu.view.utils.MguUtils;

public abstract class DTOBase implements Serializable {

	private static final long serialVersionUID = 6419615057247037869L;

	public String getVOToJson( List<?> list ) {
		return MguUtils.getJSON( list );
	}

}