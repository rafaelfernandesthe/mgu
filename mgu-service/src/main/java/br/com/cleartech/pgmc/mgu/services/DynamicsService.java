package br.com.cleartech.pgmc.mgu.services;

import br.com.cleartech.pgmc.mgu.entities.Prestadora;
import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.wsdl.dynamics.DadosRetorno;

public interface DynamicsService {

	DadosRetorno criarUsuario( Usuario usuario, Prestadora prestadoraLogada ) throws Exception;

	void alterar( Usuario usuario, boolean removerAcesso, Prestadora prestadoraLogada ) throws Exception;

	void desativarUsuario( String login, String email ) throws Exception;
}
