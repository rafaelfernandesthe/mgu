package br.com.cleartech.pgmc.mgu.integration.ws.requests;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "perfilOperadora")
public class PerfilOperadoraBean {
	private Long id;
	private String nome;
	private SistemaBean sistema;
	private List<TipoOperadoraBean> tipoOperadoras;

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

	@XmlElement(name="sistema")
	public SistemaBean getSistema() {
		return sistema;
	}

	public void setSistema(SistemaBean sistema) {
		this.sistema = sistema;
	}

	@XmlElement(name="tipoOperadoras")
	public List<TipoOperadoraBean> getTipoOperadoras() {
		return tipoOperadoras;
	}

	public void setTipoOperadoras(List<TipoOperadoraBean> tipoOperadoras) {
		this.tipoOperadoras = tipoOperadoras;
	}

	@Override
	public String toString() {
		return "PerfilOperadoraBean [id=" + id + ", nome=" + nome + ", sistema=" + sistema + ", tipoOperadoras=" + tipoOperadoras + "]";
	}
	
	
}
