package br.com.cleartech.pgmc.mgu.utils;

import com.mysema.query.support.Expressions;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.StringPath;

public class QueryUtils {

	public static BooleanExpression containsIgnoreCaseIgnoreAccents( StringPath pathField, String value ) {
		return Expressions.stringTemplate( String.format( "translate(%s, 'âàãáÁÂÀÃéêÉÊíÍóôõÓÔÕüúÜÚÇç', 'AAAAAAAAEEEEIIOOOOOOUUUUCC')", pathField.getMetadata().getElement().toString() ) ).containsIgnoreCase( StringUtils.removeAccents( value ) );
	}

}
