package br.com.cleartech.pgmc.mgu.enums;

public enum ParametrizacaoEnum {

	LDAP_ROOT( "ldap_root" ),
	LDAP_HOST( "ldap_host" ),
	LDAP_PORT( "ldap_port" ),
	LDAP_MANAGER_LOGIN( "ldap_manager_login" ),
	LDAP_MANAGER_PASSWORD( "ldap_manager_password" ),
	USER_DN_PATH( "user_dn_path_base" ),
	GRUPO_DN_MASTER( "grupo_dn_master_base" ),
	GRUPO_DN_USUARIO( "grupo_dn_usuario_base" ),
	DYNAMICS_URL( "dynamics_url" ),
	DYNAMICS_ACTIVE( "dynamics_active" ),
	MAIL_HOST( "mail_host" ),
	MAIL_PORT( "mail_port" ),
	MAIL_USERNAME( "mail_username" ),
	MAIL_PASSWORD( "mail_password" ),
	CAPTCHA_SITE_KEY( "recaptcha_site_key" ),
	CAPTCHA_SECRET_KEY( "recaptcha_secret_key" ),

	;

	private String dcParametro;

	private ParametrizacaoEnum( String dcParametro ) {
		this.dcParametro = dcParametro;
	}

	public String getDcParametro() {
		return dcParametro;
	}
}
