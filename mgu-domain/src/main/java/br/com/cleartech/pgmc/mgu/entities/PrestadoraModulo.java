package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the PRESTADORA database table.
 * 
 */
@Entity
@Table( name = "PRESTADORA_MODULO" )
@XmlRootElement( name = "PRESTADORA_MODULO" )
public class PrestadoraModulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8126380926020849620L;

	@Id
	@Column( name = "ID_PRESTADORA_MODULO" )
	private Long id;

	@ManyToOne
	@JoinColumn( name = "ID_MODULO" )
	private Modulo modulo;

	@ManyToOne
	@JoinColumn( name = "ID_PRESTADORA" )
	private Prestadora prestadora;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo( Modulo modulo ) {
		this.modulo = modulo;
	}

	public Prestadora getPrestadora() {
		return prestadora;
	}

	public void setPrestadora( Prestadora prestadora ) {
		this.prestadora = prestadora;
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
		PrestadoraModulo other = (PrestadoraModulo) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

}