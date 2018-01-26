package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The persistent class for the PRESTADORA_X_USUARIO database table.
 * 
 */
@Audited
@Entity
@Table( name = "PRESTADORA_X_USUARIO" )
@SequenceGenerator( name = "SEQ_PRESTADORA_X_USUARIO", sequenceName = "SEQ_PRESTADORA_X_USUARIO", initialValue = 1, allocationSize = 1 )
public class PrestadoraXUsuario implements Serializable {

	private static final long serialVersionUID = 1784566867041650169L;

	@Id
	@GeneratedValue( generator = "SEQ_PRESTADORA_X_USUARIO" )
	@Column( name = "PK_ID_PRESTADORA_X_USUARIO" )
	private Long id;

	// bi-directional many-to-one association to Prestadora
	@NotAudited
	@ManyToOne
	@JoinColumn( name = "PK_ID_PRESTADORA" )
	private Prestadora prestadora;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn( name = "PK_ID_USUARIO" )
	private Usuario usuario;

	public PrestadoraXUsuario() {}

	public PrestadoraXUsuario( Prestadora prestadora, Usuario usuario ) {
		super();
		this.prestadora = prestadora;
		this.usuario = usuario;
	}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public Prestadora getPrestadora() {
		return this.prestadora;
	}

	public void setPrestadora( Prestadora prestadora ) {
		this.prestadora = prestadora;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario( Usuario usuario ) {
		this.usuario = usuario;
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
		PrestadoraXUsuario other = (PrestadoraXUsuario) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PrestadoraXUsuario [id=" + id + ", prestadora=" + prestadora + ", usuario=" + usuario + "]";
	}

}