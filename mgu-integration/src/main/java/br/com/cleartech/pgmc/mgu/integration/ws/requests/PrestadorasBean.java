package br.com.cleartech.pgmc.mgu.integration.ws.requests;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="prestadora")
public class PrestadorasBean {

	private Long idPrestadora;
	private String nomePrestadora;
	private List<PrestadorasBean> prestadoras;
	
	@XmlElement(name="id_prestadora")
	public Long getIdPrestadora() {
		return idPrestadora;
	}
	public void setIdPrestadora(Long idPrestadora) {
		this.idPrestadora = idPrestadora;
	}
	
	@XmlElement(name="nome_prestadora")
	public String getNomePrestadora() {
		return nomePrestadora;
	}
	public void setNomePrestadora(String nomePrestadora) {
		this.nomePrestadora = nomePrestadora;
	}
	@XmlElement(name="prestadora")
	public List<PrestadorasBean> getPrestadoras() {
		return prestadoras;
	}
	public void setPrestadoras(List<PrestadorasBean> prestadoras) {
		this.prestadoras = prestadoras;
	}
}
