package br.com.cleartech.pgmc.mgu.utils;

import java.io.Serializable;
import java.util.List;

public abstract class DTOBase implements Serializable {

	private static final long serialVersionUID = 6419615057247037869L;

	public String getVOToJson( List<?> list ) {
		return MguUtils.getJSON( list );
	}

}