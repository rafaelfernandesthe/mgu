package br.com.cleartech.pgmc.mgu.exceptions;

public class LdapException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7367406625702342896L;

	/**
	 * Construtor da Classe.
	 */
	public LdapException() {
		super();
	}

	/**
	 * Construtor que recebe a mensagem.
	 * 
	 * @param message
	 */
	public LdapException(String message) {
		super(message);
	}

	/**
	 * Construtor que recebe a throwable.
	 * 
	 * @param throwable
	 */
	public LdapException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * @param exception
	 */
	public LdapException(Exception exception) {
		super(exception);
	}

	/**
	 * Construtor que recebe a mensagem e a exception.
	 * 
	 * @param message
	 * @param throwable
	 */
	public LdapException(String message, Throwable exception) {
		super(message, exception);
	}

}
