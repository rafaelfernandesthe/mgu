package br.com.cleartech.pgmc.mgu.integration.ws.responses;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.cleartech.pgmc.mgu.entities.Perfil;
import br.com.cleartech.pgmc.mgu.entities.Usuario;

@XmlRootElement( name = "mgu" )
public class MguResponse {

	@XmlElement( name = "retorno" )
	private Integer retorno;

	@XmlElement( name = "descricao" )
	private String descricao;

	@XmlElement( name = "usuario" )
	private Usuario usuario;

	@XmlElementWrapper( name = "perfis" )
	@XmlElement( name = "perfil" )
	private List<Perfil> perfis;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario( Usuario usuario ) {
		this.usuario = usuario;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis( List<Perfil> perfis ) {
		this.perfis = perfis;
	}

	public void addPerfil( Perfil p ) {
		this.perfis.add( p );
	}

}
