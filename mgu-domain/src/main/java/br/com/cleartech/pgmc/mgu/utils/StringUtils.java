package br.com.cleartech.pgmc.mgu.utils;

import java.text.Normalizer;

public class StringUtils {

	public static String removeAccents( String str ) {
		if ( str == null )
			return str;
		String result = Normalizer.normalize( str, Normalizer.Form.NFD );
		result = result.replaceAll( "[^\\p{ASCII}]", "" );
		return result;
	}

}
