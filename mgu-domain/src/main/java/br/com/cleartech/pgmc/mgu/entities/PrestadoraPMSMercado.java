package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "V_PRESTADORAS_PMS_MERCADO" )
public class PrestadoraPMSMercado implements Serializable {

	private static final long serialVersionUID = 5222049955930365111L;

	@Id
	@Column( name = "ID_PRESTADORA" )
	private Long idPrestadora;

	public Long getIdPrestadora() {
		return idPrestadora;
	}

	public void setIdPrestadora( Long idPrestadora ) {
		this.idPrestadora = idPrestadora;
	}

}
