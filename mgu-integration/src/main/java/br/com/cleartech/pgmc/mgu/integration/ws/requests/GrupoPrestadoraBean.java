package br.com.cleartech.pgmc.mgu.integration.ws.requests;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="grupo_prestadora")
public class GrupoPrestadoraBean {
	
	private Long idGrupoPrestadora;
	private String nomeGrupoPrestadora;
	private PrestadorasBean prestadoras;
	
	@XmlElement(name="id_grupo_prestadora")
	public Long getIdGrupoPrestadora() {
		return idGrupoPrestadora;
	}
	public void setIdGrupoPrestadora(Long idGrupoPrestadora) {
		this.idGrupoPrestadora = idGrupoPrestadora;
	}
	
	@XmlElement(name="nome_grupo_prestadora")
	public String getNomeGrupoPrestadora() {
		return nomeGrupoPrestadora;
	}
	public void setNomeGrupoPrestadora(String nomeGrupoPrestadora) {
		this.nomeGrupoPrestadora = nomeGrupoPrestadora;
	}
	
	@XmlElement(name="prestadoras")
	public PrestadorasBean getPrestadoras() {
		return prestadoras;
	}
	public void setPrestadoras(PrestadorasBean prestadoras) {
		this.prestadoras = prestadoras;
	}
}
