package br.com.cleartech.pgmc.mgu.enums;

public enum CodigoMensagem {

	RETORNO_0(0,"Dados do usuario."),
	RETORNO_1(1,"usu�rio ou senha est� incorreto."),
	RETORNO_2(2,"Usu�rio Master criado com sucesso."),
	RETORNO_3(3,"Usu�rio Master aprovado com sucesso."),
	RETORNO_4(4,"Usu�rio bloqueado pelo administrador do sistema."),
	RETORNO_5(5,"Usu�rio informado j� existe em nossa base de dados."),
	RETORNO_6(6,"N�o foi poss�vel criar o usu�rio informado."),
	RETORNO_7(7,"N�o foi poss�vel aprovar o usu�rio informado."),
	RETORNO_8(8,"Usu�rio n�o aprovado pelo administrador do sistema."),
	RETORNO_9(9,"O usu�rio j� possui a prestadora informada."),
	RETORNO_10(10,"Perfil salvo com sucesso."),
	RETORNO_11(11,"Perfil Deletado com sucesso."),
	RETORNO_12(12,"Erro ao deletar perfil."),
	RETORNO_13(13,"Perfil informado n�o existe na base de dados."),
	RETORNO_14(14,"Erro ao salvar o perfil."),
	RETORNO_15(15,"Prestadora associada com sucesso ao usu�rio."),
	RETORNO_16(16,"Prestadora informada n�o existe na base de dados."),
	RETORNO_17(17,"Grupo prestadora do usu�rio n�o pode ser diferente da prestadora informada."),
	RETORNO_18(18,"N�o foi poss�vel associar a prestadora ao usu�rio."),
	RETORNO_19(19,"Sistema informado n�o existe na base de dados."),
	RETORNO_20(20,"Lista de Perfis."),
	RETORNO_21(21,"Sistema n�o tem nenhum perfil cadastrado."),
	RETORNO_22(22,"Senha expirada ou tempor�ria, a mesma deve ser trocada para ter acesso ao sistema."),
	RETORNO_23(23,"Usu�rio esta com a senha provis�ria e ainda n�o foi aprovado no credenciamento."),
	RETORNO_24(24,"Usu�rio n�o tem nenhum perfil associado ao sistema informado."),
	RETORNO_25(25,"CPF informado j� existe em nossa base de dados."),
	RETORNO_26(26,"Usu�rio n�o tem acesso ao Dynamics"),
	RETORNO_27(27,"Usu�rio informado esta com caracter invalido."),
	RETORNO_28(28,"Ap�s mais uma tentativa invalida, o usu�rio ser� bloaqueado por excesso de tentativas invalidas de acesso."),
	RETORNO_29(29,"Usuario Bloaqueado por excesso de tentativas invalidas de acesso."),
	RETORNO_30(30,"Usu�rio informado j� possui uma sess�o ativa para o sistema."),
	
	RETORNO_31(31,"Usu�rio bloqueado por inatividade, favor entrar em contato com o administrador de sua prestadora."),
	RETORNO_32(32,"Senha do usu�rio expirada, favor atualizar a senha antes de prosseguir com o acesso.");
	
	private Integer codigo;
	private String descricao;
		
	CodigoMensagem(Integer codigo,String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}


	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}	
}