package br.com.cleartech.pgmc.mgu.enums;

public enum ParametrizacaoEnum {

	USER_DN_PATH_BASE( "user_dn_path_base" ),
	DYNAMICS_URL( "dynamics_url" );
	
	private String dcParametro;

	private ParametrizacaoEnum( String dcParametro ) {
		this.dcParametro = dcParametro;
	}

	public String getDcParametro() {
		return dcParametro;
	}
}
