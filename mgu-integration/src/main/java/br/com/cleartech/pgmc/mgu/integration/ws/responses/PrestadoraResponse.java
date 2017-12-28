package br.com.cleartech.pgmc.mgu.integration.ws.responses;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

@XmlRootElement( name = "prestadora" )
public class PrestadoraResponse {

	@XmlElement( name = "id_prestadora" )
	private Long id;

	@XmlElement( name = "nome_prestadora" )
	@JacksonXmlCData
	private String nome;

	@XmlElement( name = "master" )
	private Integer master;

	@XmlElementWrapper( name = "perfis" )
	@XmlElement( name = "perfil" )
	private List<PerfilResponse> perfis;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public Integer getMaster() {
		return master;
	}

	public void setMaster( Integer master ) {
		this.master = master;
	}

	public List<PerfilResponse> getPerfis() {
		if (perfis == null)
			perfis = new ArrayList<PerfilResponse>();
		return perfis;
	}

	public void setPerfis( List<PerfilResponse> perfil ) {
		this.perfis = perfil;
	}
}
