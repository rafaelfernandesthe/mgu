package br.com.cleartech.pgmc.mgu.services;

import javax.xml.bind.JAXBException;

import br.com.cleartech.pgmc.mgu.entities.Usuario;
import br.com.cleartech.pgmc.mgu.wsdl.dynamics.DadosRetorno;

public interface DynamicsService {

	DadosRetorno criarUsuario( Usuario usuario ) throws JAXBException;

	void alterar( Usuario usuario, boolean removerAcesso ) throws JAXBException;

	void desativarUsuario( String login, String email );
}
