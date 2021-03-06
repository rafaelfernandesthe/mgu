package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

/**
 * The persistent class for the SISTEMA database table.
 * 
 */
@Audited
@Entity
@Table( name = "SISTEMA" )
@SequenceGenerator( name = "SEQ_SISTEMA", sequenceName = "SEQ_SISTEMA", initialValue = 1, allocationSize = 1 )
@XmlRootElement( name = "sistema" )
public class Sistema implements Serializable {

	private static final long serialVersionUID = 4940984505758366601L;

	@Id
	@OrderBy
	@GeneratedValue( generator = "SEQ_SISTEMA" )
	@Column( name = "PK_ID_SISTEMA" )
	private Long id;

	@Column( name = "DC_SISTEMA" )
	private String dcSistema;

	public Sistema() {}

	public Sistema( String dcSistema ) {
		this.setDcSistema( dcSistema );
	}

	@XmlElement( name = "id_sistema" )
	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	@XmlElement( name = "nome_sistema" )
	public String getDcSistema() {
		return this.dcSistema;
	}

	public void setDcSistema( String dcSistema ) {
		this.dcSistema = dcSistema;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Sistema other = (Sistema) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sistema [id=" + id + ", dcSistema=" + dcSistema + "]";
	}

}