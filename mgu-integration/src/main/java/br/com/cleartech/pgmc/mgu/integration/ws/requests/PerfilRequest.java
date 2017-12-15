package br.com.cleartech.pgmc.mgu.integration.ws.requests;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="perfil")
public class PerfilRequest {

	private Long id;
	private String nome;
	private SistemaBean sistema;
	
	@XmlElement(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@XmlElement(name="nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public SistemaBean getSistema() {
		return sistema;
	}
	
	public void setSistema(SistemaBean sistema) {
		this.sistema = sistema;
	}
	
}
