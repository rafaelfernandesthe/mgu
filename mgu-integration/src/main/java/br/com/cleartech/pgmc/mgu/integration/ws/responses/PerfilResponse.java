package br.com.cleartech.pgmc.mgu.integration.ws.responses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "perfil" )
public class PerfilResponse {

	@XmlElement( name = "id" )
	private Long id;

	@XmlElement( name = "nome" )
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

}
