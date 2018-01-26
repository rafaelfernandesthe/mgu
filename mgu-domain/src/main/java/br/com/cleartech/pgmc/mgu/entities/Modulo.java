package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the PRESTADORA database table.
 * 
 */
@Entity
@Table( name = "MODULO" )
@XmlRootElement( name = "MODULO" )
public class Modulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6603254076317355781L;

	@Id
	@Column( name = "ID_MODULO" )
	private Long id;

	@Column( name = "DC_MODULO" )
	private String dcModulo;

	@OneToMany( mappedBy = "modulo" )
	private List<PrestadoraModulo> prestadoras;

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getDcModulo() {
		return dcModulo;
	}

	public void setDcModulo( String dcModulo ) {
		this.dcModulo = dcModulo;
	}

	public List<PrestadoraModulo> getPrestadoras() {
		if ( prestadoras == null ) {
			prestadoras = new ArrayList<PrestadoraModulo>();
		}
		return prestadoras;
	}

	public void setPrestadoras( List<PrestadoraModulo> prestadoras ) {
		this.prestadoras = prestadoras;
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
		Modulo other = (Modulo) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

}