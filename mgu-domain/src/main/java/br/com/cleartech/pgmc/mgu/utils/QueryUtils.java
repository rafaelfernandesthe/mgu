package br.com.cleartech.pgmc.mgu.utils;

import com.mysema.query.support.Expressions;
import com.mysema.query.types.expr.BooleanExpression;

public class QueryUtils {

	public static BooleanExpression containsIgnoreCaseIgnoreAccents( String fieldName, String value ) {
		return Expressions.stringTemplate( String.format( "translate(%s, 'âàãáÁÂÀÃéêÉÊíÍóôõÓÔÕüúÜÚÇç', 'AAAAAAAAEEEEIIOOOOOOUUUUCC')", fieldName ) ).containsIgnoreCase( StringUtils.removeAccents( value ) );
	}

}
