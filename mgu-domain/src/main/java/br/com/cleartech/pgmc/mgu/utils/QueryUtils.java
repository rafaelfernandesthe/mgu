package br.com.cleartech.pgmc.mgu.utils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;

public class QueryUtils {

	public static BooleanExpression containsIgnoreCaseIgnoreAccents( StringPath pathField, String value ) {
		return Expressions.stringTemplate( String.format( "translate(%s, 'âàãáÁÂÀÃéêÉÊíÍóôõÓÔÕüúÜÚÇç', 'AAAAAAAAEEEEIIOOOOOOUUUUCC')", pathField.getMetadata().getElement().toString() ) ).containsIgnoreCase( StringUtils.removeAccents( value ) );
	}

}
