package br.com.cleartech.pgmc.mgu.integration.ws.requests;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "usuario" )
public class LogoutRequest {

	private String usuario;
	private String sistema;

	@XmlElement( name = "usuario" )
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario( String usuario ) {
		this.usuario = usuario;
	}

	@XmlElement( name = "sistema" )
	public String getSistema() {
		return sistema;
	}

	public void setSistema( String sistema ) {
		this.sistema = sistema;
	}

	@Override
	public String toString() {
		return "LogoutRequest [usuario=" + usuario + ", sistema=" + sistema + "]";
	}

}
