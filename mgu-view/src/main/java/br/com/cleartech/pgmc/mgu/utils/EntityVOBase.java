package br.com.cleartech.pgmc.mgu.utils;

import java.io.Serializable;
import java.util.List;

public abstract class EntityVOBase implements Serializable {

	private static final long serialVersionUID = -3607176027988973185L;

	public abstract List<EntityVOBase> getVO( List<?> list );

	public String getVOToJson( List<?> list ) {
		return MguUtils.getJson( getVO( list ) );
	}

}