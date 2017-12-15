package br.com.cleartech.pgmc.mgu.exceptions;

public class DynamicsException extends RuntimeException {

	private static final long serialVersionUID = 6125114276189063864L;

	/**
	 * Construtor da Classe.
	 */
	public DynamicsException() {
		super();
	}

	/**
	 * Construtor que recebe a mensagem.
	 * 
	 * @param message
	 */
	public DynamicsException( String message ) {
		super( message );
	}

	/**
	 * Construtor que recebe a throwable.
	 * 
	 * @param throwable
	 */
	public DynamicsException( Throwable throwable ) {
		super( throwable );
	}

	/**
	 * @param exception
	 */
	public DynamicsException( Exception exception ) {
		super( exception );
	}

	/**
	 * Construtor que recebe a mensagem e a exception.
	 * 
	 * @param message
	 * @param throwable
	 */
	public DynamicsException( String message, Throwable exception ) {
		super( message, exception );
	}

}
