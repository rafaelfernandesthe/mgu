package br.com.cleartech.pgmc.mgu.integration.ws.responses;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

@XmlRootElement( name = "grupo_prestadora" )
public class GrupoPrestadoraResponse {

	@XmlElement( name = "id_grupo_prestadora" )
	private Long idGrupoPrestadora;

	@XmlElement( name = "nome_grupo_prestadora" )
	@JacksonXmlCData
	private String nomeGrupoPrestadora;

	@XmlElementWrapper( name = "prestadoras" )
	@XmlElement( name = "prestadora" )
	private List<PrestadoraResponse> prestadoras;

	public Long getIdGrupoPrestadora() {
		return idGrupoPrestadora;
	}

	public void setIdGrupoPrestadora( Long idGrupoPrestadora ) {
		this.idGrupoPrestadora = idGrupoPrestadora;
	}

	public String getNomeGrupoPrestadora() {
		return nomeGrupoPrestadora;
	}

	public void setNomeGrupoPrestadora( String nomeGrupoPrestadora ) {
		this.nomeGrupoPrestadora = nomeGrupoPrestadora;
	}

	public List<PrestadoraResponse> getPrestadoras() {
		if ( prestadoras == null )
			prestadoras = new ArrayList<PrestadoraResponse>();
		return prestadoras;
	}

	public void setPrestadoras( List<PrestadoraResponse> prestadoras ) {
		this.prestadoras = prestadoras;
	}

}
