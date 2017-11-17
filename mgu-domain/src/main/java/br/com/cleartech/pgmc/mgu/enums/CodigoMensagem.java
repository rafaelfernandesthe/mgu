package br.com.cleartech.pgmc.mgu.enums;

public enum CodigoMensagem {

	RETORNO_0(0,"Dados do usuario."),
	RETORNO_1(1,"usuário ou senha está incorreto."),
	RETORNO_2(2,"Usuário Master criado com sucesso."),
	RETORNO_3(3,"Usuário Master aprovado com sucesso."),
	RETORNO_4(4,"Usuário bloqueado pelo administrador do sistema."),
	RETORNO_5(5,"Usuário informado já existe em nossa base de dados."),
	RETORNO_6(6,"Não foi possível criar o usuário informado."),
	RETORNO_7(7,"Não foi possível aprovar o usuário informado."),
	RETORNO_8(8,"Usuário não aprovado pelo administrador do sistema."),
	RETORNO_9(9,"O usuário já possui a prestadora informada."),
	RETORNO_10(10,"Perfil salvo com sucesso."),
	RETORNO_11(11,"Perfil Deletado com sucesso."),
	RETORNO_12(12,"Erro ao deletar perfil."),
	RETORNO_13(13,"Perfil informado não existe na base de dados."),
	RETORNO_14(14,"Erro ao salvar o perfil."),
	RETORNO_15(15,"Prestadora associada com sucesso ao usuário."),
	RETORNO_16(16,"Prestadora informada não existe na base de dados."),
	RETORNO_17(17,"Grupo prestadora do usuário não pode ser diferente da prestadora informada."),
	RETORNO_18(18,"Não foi possível associar a prestadora ao usuário."),
	RETORNO_19(19,"Sistema informado não existe na base de dados."),
	RETORNO_20(20,"Lista de Perfis."),
	RETORNO_21(21,"Sistema não tem nenhum perfil cadastrado."),
	RETORNO_22(22,"Senha expirada ou temporária, a mesma deve ser trocada para ter acesso ao sistema."),
	RETORNO_23(23,"Usuário esta com a senha provisória e ainda não foi aprovado no credenciamento."),
	RETORNO_24(24,"Usuário não tem nenhum perfil associado ao sistema informado."),
	RETORNO_25(25,"CPF informado já existe em nossa base de dados."),
	RETORNO_26(26,"Usuário não tem acesso ao Dynamics"),
	RETORNO_27(27,"Usuário informado esta com caracter invalido."),
	RETORNO_28(28,"Após mais uma tentativa invalida, o usuário será bloaqueado por excesso de tentativas invalidas de acesso."),
	RETORNO_29(29,"Usuario Bloaqueado por excesso de tentativas invalidas de acesso."),
	RETORNO_30(30,"Usuário informado já possui uma sessão ativa para o sistema."),
	
	RETORNO_31(31,"Usuário bloqueado por inatividade, favor entrar em contato com o administrador de sua prestadora."),
	RETORNO_32(32,"Senha do usuário expirada, favor atualizar a senha antes de prosseguir com o acesso.");
	
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