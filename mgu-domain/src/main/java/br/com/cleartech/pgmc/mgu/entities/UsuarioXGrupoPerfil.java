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

/**
 * The persistent class for the USUARIO_X_GRUPO_PERFIL database table.
 * 
 */
@Audited
@Entity
@Table( name = "USUARIO_X_GRUPO_PERFIL" )
@SequenceGenerator( name = "SEQ_USUARIO_X_GRUPO_PERFIL", sequenceName = "SEQ_USUARIO_X_GRUPO_PERFIL", initialValue = 1, allocationSize = 1 )
public class UsuarioXGrupoPerfil implements Serializable {

	private static final long serialVersionUID = -423962683037228133L;

	@Id
	@GeneratedValue( generator = "SEQ_USUARIO_X_GRUPO_PERFIL" )
	@Column( name = "PK_ID_USUARIO_X_GRUPO_PERFIL" )
	private Long id;

	// bi-directional many-to-one association to GrupoPerfil
	@ManyToOne
	@JoinColumn( name = "PK_ID_GRUPO_PERFIL" )
	private GrupoPerfil grupoPerfil;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn( name = "PK_ID_USUARIO" )
	private Usuario usuario;

	public UsuarioXGrupoPerfil() {}

	public UsuarioXGrupoPerfil( GrupoPerfil grupoPerfil, Usuario usuario ) {
		super();
		this.grupoPerfil = grupoPerfil;
		this.usuario = usuario;
	}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public GrupoPerfil getGrupoPerfil() {
		return this.grupoPerfil;
	}

	public void setGrupoPerfil( GrupoPerfil grupoPerfil ) {
		this.grupoPerfil = grupoPerfil;
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
		result = prime * result + (int) ( id ^ ( id >>> 32 ) );
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
		UsuarioXGrupoPerfil other = (UsuarioXGrupoPerfil) obj;
		if ( id != other.id )
			return false;
		return true;
	}

}