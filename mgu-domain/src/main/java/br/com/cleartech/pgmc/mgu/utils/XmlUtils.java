package br.com.cleartech.pgmc.mgu.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlUtils {

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
