package br.com.cleartech.pgmc.mgu.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;


/**
 * The persistent class for the ACESSO_SIMULTANEO database table.
 * 
 */
@Audited
@Entity
@Table(name="ACESSO_SIMULTANEO")
@SequenceGenerator(name = "SEQ_ACESSO_SIMULTANEO", sequenceName = "SEQ_ACESSO_SIMULTANEO", initialValue=1, allocationSize=1)
public class AcessoSimultaneo implements Serializable {

	private static final long serialVersionUID = 4689172472945264839L;

	@Id
	@GeneratedValue(generator = "SEQ_ACESSO_SIMULTANEO")
	@Column(name="ID_ACESSO_SIMULTANEO")
	private Long id;

	@Column(name="USERNAME")
	private String username;
	
	@Column(name="SISTEMA")
	private String sistema;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_ACESSO")
	private Date dtAcesso;
	
	public AcessoSimultaneo() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}


	public Date getDtAcesso() {
		return dtAcesso;
	}

	public void setDtAcesso(Date dtAcesso) {
		this.dtAcesso = dtAcesso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AcessoSimultaneo other = (AcessoSimultaneo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}