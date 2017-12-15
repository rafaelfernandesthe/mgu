package br.com.cleartech.pgmc.mgu.integration.ws.responses;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "usuario" )
public class UsuarioResponse {

	@XmlElement( name = "nome" )
	private String nomeUsuario;

	@XmlElement( name = "telefone" )
	private String telefone;

	@XmlElement( name = "email" )
	private String email;

	@XmlElement( name = "ip_origem" )
	private String ipOrigem;

	@XmlElement( name = "situacao" )
	private String situacao;

	@XmlElement( name = "primeiro_acesso" )
	private Integer primeiroAcesso;

	@XmlElement( name = "grupo_prestadora" )
	private GrupoPrestadoraResponse grupoPrestadora;

	@XmlElementWrapper( name = "perfis" )
	@XmlElement( name = "perfil" )
	private List<PerfilResponse> perfis;

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario( String nomeUsuario ) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone( String telefone ) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}

	public String getIpOrigem() {
		return ipOrigem;
	}

	public void setIpOrigem( String ipOrigem ) {
		this.ipOrigem = ipOrigem;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao( String situacao ) {
		this.situacao = situacao;
	}

	public Integer getPrimeiroAcesso() {
		return primeiroAcesso;
	}

	public void setPrimeiroAcesso( Integer primeiroAcesso ) {
		this.primeiroAcesso = primeiroAcesso;
	}

	public GrupoPrestadoraResponse getGrupoPrestadora() {
		return grupoPrestadora;
	}

	public void setGrupoPrestadora( GrupoPrestadoraResponse grupoPrestadora ) {
		this.grupoPrestadora = grupoPrestadora;
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
