package br.com.cleartech.pgmc.mgu.integration.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "mgu" )
public class MguRespostaPerfil {

	private Integer retorno;

	private String descricao;

	private String idPerfil;

	@XmlElement( name = "retorno" )
	public Integer getRetorno() {
		return retorno;
	}

	public void setRetorno( Integer retorno ) {
		this.retorno = retorno;
	}

	@XmlElement( name = "descricao" )
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao( String descricao ) {
		this.descricao = descricao;
	}

	@XmlElement( name = "idPerfil" )
	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil( String idPerfil ) {
		this.idPerfil = idPerfil;
	}

}
