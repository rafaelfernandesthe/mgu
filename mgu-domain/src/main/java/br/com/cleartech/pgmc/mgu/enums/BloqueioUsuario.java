package br.com.cleartech.pgmc.mgu.enums;

public enum BloqueioUsuario {

	BLOQUEADO_NAO( 0 ),
	BLOQUEADO_ADM( 1 ),
	BLOQUEADO_PRIMEIROACESSO( 2 ),
	BLOQUEADO_EXPIRADA( 3 ),
	BLOQUEADO_TENTATIVA( 4 );

	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue( Integer value ) {
		this.value = value;
	}

	BloqueioUsuario( Integer value ) {
		this.value = value;
	}

	@Override
	public String toString() {
		switch ( this ) {
			case BLOQUEADO_NAO:
				return "Ativo";

			case BLOQUEADO_ADM:
				return "Bloqueado";

			case BLOQUEADO_PRIMEIROACESSO:
				return "Primeiro acesso, trocar senha";

			case BLOQUEADO_EXPIRADA:
				return "Senha expirada";

			case BLOQUEADO_TENTATIVA:
				return "Excesso de Tentativas invalida";

			default:
				return "---";
		}
	}

	public String getI18n() {
		switch ( this ) {
			case BLOQUEADO_NAO:
				return "Ativo";

			case BLOQUEADO_ADM:
				return "Bloqueado";

			case BLOQUEADO_PRIMEIROACESSO:
				return "Primeiro acesso, trocar senha";

			case BLOQUEADO_EXPIRADA:
				return "Senha expirada";

			case BLOQUEADO_TENTATIVA:
				return "Excesso de Tentativas invalida";

			default:
				return "---";
		}
	}

	public static Integer toInt( BloqueioUsuario enumObject ) {
		if ( enumObject == null ) {
			return null;
		}
		return enumObject.value;
	}

	public Integer toInt() {
		return value;
	}

	public static BloqueioUsuario getByInt( Integer i ) {
		for ( BloqueioUsuario b : BloqueioUsuario.values() ) {
			if ( b.getValue().equals( i ) )
				return b;
		}
		return null;
	}

}