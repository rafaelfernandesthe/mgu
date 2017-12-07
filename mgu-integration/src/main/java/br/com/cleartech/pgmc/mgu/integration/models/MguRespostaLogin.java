package br.com.cleartech.pgmc.mgu.integration.models;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import br.com.cleartech.pgmc.mgu.entities.Usuario;

@XmlRootElement( name = "mgu" )
@XmlType( propOrder = { "retorno", "descricao", "usuario" } )
public class MguRespostaLogin {

	private Integer retorno;

	private String descricao;

	private List<Usuario> usuario;

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

	@XmlElementWrapper( name = "dados" )
	@XmlElement( name = "usuario" )
	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario( List<Usuario> usuario ) {
		this.usuario = usuario;
	}

}
