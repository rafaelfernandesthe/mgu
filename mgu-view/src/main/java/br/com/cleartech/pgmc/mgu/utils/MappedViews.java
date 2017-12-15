package br.com.cleartech.pgmc.mgu.utils;

public enum MappedViews {

	LOGIN( "/login.html" ),
	HOME( "/home.html" ),
	USUARIO_CADASTRO( "/usuario/usuarioCadastro.html" ),
	USUARIO_CONSULTA( "/usuario/usuarioConsulta.html" ),
	GRUPO_PERFIL_CADASTRO( "/grupoPerfil/grupoPerfilCadastro.html" ),
	GRUPO_PERFIL_CONSULTA( "/grupoPerfil/grupoPerfilConsulta" ),
	PARAMETRO_CONSULTA( "/parametro/parametroConsulta.html" ),

	/**
	 * Mostra a mensagem "Operação Realizada com Sucesso"
	 */
	SUCESS_PARAMETER( "?sucess=1" ),

	;

	String path;

	public String getPath() {
		return path;
	}

	private MappedViews( String path ) {
		this.path = path;
	}

}
