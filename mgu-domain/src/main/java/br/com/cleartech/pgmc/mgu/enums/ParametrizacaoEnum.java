package br.com.cleartech.pgmc.mgu.enums;

public enum ParametrizacaoEnum {

	LDAP_ROOT( "ldap_root" ),
	USER_DN_PATH( "user_dn_path_base" ),
	GRUPO_DN_MASTER( "grupo_dn_master_base" ),
	GRUPO_DN_USUARIO( "grupo_dn_usuario_base" ),
	DYNAMICS_URL( "dynamics_url" ),
	DYNAMICS_ACTIVE( "dynamics_active" );
	
	private String dcParametro;

	private ParametrizacaoEnum( String dcParametro ) {
		this.dcParametro = dcParametro;
	}

	public String getDcParametro() {
		return dcParametro;
	}
}
