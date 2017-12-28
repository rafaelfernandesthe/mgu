package br.com.cleartech.pgmc.mgu.integration.ws.responses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

@XmlRootElement( name = "mgu" )
public class MguLoginResponse {

	@XmlElement( name = "retorno" )
	private Integer retorno;

	@XmlElement( name = "descricao" )
	@JacksonXmlCData
	private String descricao;

	@XmlElement( name = "dados" )
	private DadosResponse dados;
	
	@XmlTransient
	private Boolean semPrestadora = true;
	
	@XmlTransient
	private Boolean isPMS = false;

	@XmlTransient
	private String perfilGed;

	public Integer getRetorno() {
		return retorno;
	}

	public void setRetorno( Integer retorno ) {
		this.retorno = retorno;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	public DadosResponse getDados() {
		return dados;
	}

	public void setDados( DadosResponse dados ) {
		this.dados = dados;
	}

	public Boolean getSemPrestadora() {
		return semPrestadora;
	}

	public void setSemPrestadora( Boolean semPrestadora ) {
		this.semPrestadora = semPrestadora;
	}

	public Boolean getIsPMS() {
		return isPMS;
	}

	public void setIsPMS( Boolean isPMS ) {
		this.isPMS = isPMS;
	}

	public String getPerfilGed() {
		return perfilGed;
	}

	public void setPerfilGed( String perfilGed ) {
		this.perfilGed = perfilGed;
	}
	
}