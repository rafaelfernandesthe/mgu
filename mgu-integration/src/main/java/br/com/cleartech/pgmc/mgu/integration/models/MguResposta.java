package br.com.cleartech.pgmc.mgu.integration.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.Usuario;

@XmlRootElement( name = "mgu" )
public class MguResposta {

	private Integer retorno;

	private String descricao;

	private Usuario usuario;

	private Perfil perfil;

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

	@XmlElement( name = "usuario" )
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario( Usuario usuario ) {
		this.usuario = usuario;
	}

	@XmlElement( name = "perfis" )
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil( Perfil perfil ) {
		this.perfil = perfil;
	}

}
