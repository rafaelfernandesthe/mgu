package br.com.cleartech.pgmc.mgu.integration.ws.requests;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="perfis")
public class PerfisBean {

	private Long idPerfil;
	private String nomePerfil;
	private List<PerfisBean> listaPerfil;
	
	@XmlElement(name="id")
	public Long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	
	@XmlElement(name="nome")
	public String getNomePerfil() {
		return nomePerfil;
	}
	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}
	
	@XmlElement(name="perfil")
	public List<PerfisBean> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<PerfisBean> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}
	
	
	
}
