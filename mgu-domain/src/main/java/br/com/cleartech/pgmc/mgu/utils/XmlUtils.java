package br.com.cleartech.pgmc.mgu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class XmlUtils {

	private static final String CDATA_TAG_START = "<![CDATA[";
	private static final String CDATA_TAG_END = "]]>";

	public static String cdataWrapper( Object value ) {

		if ( StringUtils.isEmpty( value ) ) {
			return String.valueOf( value );
		}

		if ( org.apache.commons.lang.StringUtils.isAlphanumericSpace( String.valueOf( value ) ) ) {
			return String.valueOf( value );
		}

		return CDATA_TAG_START + String.valueOf( String.valueOf( value ) ) + CDATA_TAG_END;

	}

	public static boolean contemCaracterEspecial( String nome ) {
		boolean valida = Boolean.FALSE;
		Character[] caracter = { '*', '\'', '(', ')', '&', '[', ']', '`', '~', '|', '@', '$', '%', '^', '?', ':', '{', '}', '!', '+', ';', '/', '\\' };
		for ( Character ite : caracter ) {
			valida = nome.contains( ite.toString() );
			if ( valida ) {
				break;
			}
		}
		return valida;
	}

	public static Boolean validarEmail( String email ) {
		if ( email == null || email.isEmpty() ) {
			return false;
		}
		Pattern p = Pattern.compile( "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$" );
		Matcher m = p.matcher( email );
		if ( m.find() ) {
			return true;
		} else {
			return false;
		}
	}
}
