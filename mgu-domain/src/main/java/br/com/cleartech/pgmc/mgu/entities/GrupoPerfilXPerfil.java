package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
 * The persistent class for the GRUPO_PERFIL_X_PERFIL database table.
 * 
 */
@Audited
@Entity
@Table( name = "GRUPO_PERFIL_X_PERFIL" )
@SequenceGenerator( name = "SEQ_GRUPO_PERFIL_X_PERFIL", sequenceName = "SEQ_GRUPO_PERFIL_X_PERFIL", initialValue = 1, allocationSize = 1 )
public class GrupoPerfilXPerfil implements Serializable {

	private static final long serialVersionUID = 8015220686052268735L;

	@Id
	@GeneratedValue( generator = "SEQ_GRUPO_PERFIL_X_PERFIL" )
	@Column( name = "PK_ID_GRUPO_PERFIL_X_PERFIL" )
	private Long id;

	// bi-directional many-to-one association to GrupoPerfil
	@ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn( name = "PK_ID_GRUPO_PERFIL" )
	private GrupoPerfil grupoPerfil;

	// bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn( name = "PK_ID_PERFIL" )
	private Perfil perfil;

	public GrupoPerfilXPerfil() {}

	public GrupoPerfilXPerfil( GrupoPerfil grupoPerfil, Perfil perfil ) {
		this.grupoPerfil = grupoPerfil;
		this.perfil = perfil;
	}

	public Long getId() {
		return id;
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

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil( Perfil perfil ) {
		this.perfil = perfil;
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
		GrupoPerfilXPerfil other = (GrupoPerfilXPerfil) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

}