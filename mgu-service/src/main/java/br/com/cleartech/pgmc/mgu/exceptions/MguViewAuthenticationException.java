package br.com.cleartech.pgmc.mgu.exceptions;

import org.springframework.security.core.AuthenticationException;

public class MguViewAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = -6674223581783006708L;

	public MguViewAuthenticationException( String msg ) {
		super( msg );
	}

}