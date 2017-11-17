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

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import br.com.cleartech.pgmc.mgu.listeners.HistRevisaoListener;


/**
 * The persistent class for the ACAO database table.
 * 
 */
@Entity
@RevisionEntity(HistRevisaoListener.class)
@Table(name="HIST_REVISAO")
@SequenceGenerator(name = "SEQ_HIST_REVISAO", sequenceName = "S_HIST_REVISAO" )
public class HistRevisao implements Serializable {
	
	private static final long serialVersionUID = 2225083333000746851L;

	@Id
	@GeneratedValue(generator = "SEQ_HIST_REVISAO")
	@Column(name="CD_REVISAO")
	@RevisionNumber
	private Long cdRevisao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@RevisionTimestamp
	@Column(name="DT_REVISAO")
	private Date dtRevisao;
	
	@Column(name="dc_USER_NAME")
	private String userName;

	public Long getCdRevisao() {
		return cdRevisao;
	}

	public void setCdRevisao(Long cdRevisao) {
		this.cdRevisao = cdRevisao;
	}

	public Date getDtRevisao() {
		return dtRevisao;
	}

	public void setDtRevisao(Date dtRevisao) {
		this.dtRevisao = dtRevisao;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getCdRevisao() == null) ? 0 : getCdRevisao().hashCode());
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
		HistRevisao other = (HistRevisao) obj;
		if (getCdRevisao() == null) {
			if (other.getCdRevisao() != null)
				return false;
		} else if (!getCdRevisao().equals(other.getCdRevisao()))
			return false;
		return true;
	}
	
}