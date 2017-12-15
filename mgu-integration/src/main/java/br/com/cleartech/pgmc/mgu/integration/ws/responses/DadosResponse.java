package br.com.cleartech.pgmc.mgu.integration.ws.responses;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "dados" )
public class DadosResponse {

	@XmlElement( name = "usuario" )
	private UsuarioResponse usuario;

	@XmlElementWrapper( name = "perfis" )
	@XmlElement( name = "perfil" )
	private List<PerfilResponse> perfis;

	public UsuarioResponse getUsuario() {
		return usuario;
	}

	public void setUsuario( UsuarioResponse usuario ) {
		this.usuario = usuario;
	}

	public List<PerfilResponse> getPerfis() {
		if ( perfis == null )
			perfis = new ArrayList<PerfilResponse>();
		return perfis;
	}

	public void setPerfis( List<PerfilResponse> perfis ) {
		this.perfis = perfis;
	}

}
