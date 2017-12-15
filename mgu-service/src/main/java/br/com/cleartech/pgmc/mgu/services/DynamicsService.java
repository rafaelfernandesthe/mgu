package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.wsdl.dynamics.DadosRetorno;

public interface DynamicsService {

	DadosRetorno criarUsuario( Usuario usuario ) throws Exception;

	void alterar( Usuario usuario, boolean removerAcesso ) throws Exception;

	void desativarUsuario( String login, String email ) throws Exception;
}
