package br.com.cleartech.pgmc.mgu.exceptions;

public class MguException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7367406625702342896L;

	/**
	 * Construtor da Classe.
	 */
	public MguException() {
		super();
	}

	/**
	 * Construtor que recebe a mensagem.
	 * 
	 * @param message
	 */
	public MguException(String message) {
		super(message);
	}

	/**
	 * Construtor que recebe a throwable.
	 * 
	 * @param throwable
	 */
	public MguException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * @param exception
	 */
	public MguException(Exception exception) {
		super(exception);
	}

	/**
	 * Construtor que recebe a mensagem e a exception.
	 * 
	 * @param message
	 * @param throwable
	 */
	public MguException(String message, Throwable exception) {
		super(message, exception);
	}

}
