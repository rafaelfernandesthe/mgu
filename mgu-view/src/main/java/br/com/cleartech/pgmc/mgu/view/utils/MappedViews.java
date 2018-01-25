package br.com.cleartech.pgmc.mgu.view.utils;

public enum MappedViews {

	LOGIN( "/login.html" ),
	HOME( "/home.html" ),
	PROBLEMA_SENHA( "/problemaSenha.html" ),
	TROCAR_SENHA( "/trocarSenha.html" ),
	USUARIO_CADASTRO( "/usuario/usuarioCadastro.html" ),
	USUARIO_CONSULTA( "/usuario/usuarioConsulta.html" ),
	USUARIO_EDICAO( "/usuario/usuarioEdicao.html" ),
	GRUPO_PERFIL_CADASTRO( "/grupoPerfil/grupoPerfilCadastro.html" ),
	GRUPO_PERFIL_CONSULTA( "/grupoPerfil/grupoPerfilConsulta.html" ),
	GRUPO_PERFIL_EDICAO( "/grupoPerfil/grupoPerfilEdicao.html" ),
	PARAMETRO_CONSULTA( "/parametro/parametroConsulta.html" ),
	PARAMETRO_ESOA_CONSULTA( "/parametro/parametroEsoaConsulta.html" ),

	/**
	 * Mostra a mensagem "Operação Realizada com Sucesso"
	 */
	SUCESSO_PARAMETRO_NOVO( "?success=1&msgText=%s" ),
	SUCESSO_PARAMETRO_COMPEMENTO( "&success=1&msgText=%s" ),

	/**
	 * Mostra a mensagem de erro de email
	 */
	SUCESSO_COM_ALERTA_EMAIL_PARAMETRO_NOVO( "?success=1&msgAlertaEmail=%s&msgText=%s" ),
	SUCESSO_COM_ALERTA_EMAIL_PARAMETRO_COMPLEMENTO( "&success=1&msgAlertaEmail=%s&msgText=%s" ),

	;

	String path;

	public String getPath() {
		return path;
	}

	private MappedViews( String path ) {
		this.path = path;
	}

}
