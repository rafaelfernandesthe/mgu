package br.com.cleartech.pgmc.mgu.integration.ws.requests;

import javax.xml.bind.annotation.XmlElement;

public class TipoOperadoraBean {
	private String nome;

	@XmlElement( name = "nome" )
	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "TipoOperadoraBean [nome=" + nome + "]";
	}

}
