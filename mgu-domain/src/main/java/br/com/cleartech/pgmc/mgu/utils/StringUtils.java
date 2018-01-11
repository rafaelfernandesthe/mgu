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

	public static boolean isEmpty( String value ) {
		if ( org.springframework.util.StringUtils.isEmpty( value ) || org.springframework.util.StringUtils.isEmpty( value.trim() ) || "null".equals( value ) ) {
			return true;
		}

		return false;
	}

	public static boolean hasAnyEmpty( String... value ) {
		for ( String i : value ) {
			if ( isEmpty( i ) )
				return true;
		}
		return false;
	}

}
