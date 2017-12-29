package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The persistent class for the GRUPO_PERFIL database table.
 * 
 */
@Audited
@Entity
@Table( name = "GRUPO_PERFIL" )
@SequenceGenerator( name = "SEQ_GRUPO_PERFIL", sequenceName = "SEQ_GRUPO_PERFIL", initialValue = 1, allocationSize = 1 )
public class GrupoPerfil implements Serializable {

	private static final long serialVersionUID = 4689172472945264839L;

	@Id
	@GeneratedValue( generator = "SEQ_GRUPO_PERFIL" )
	@Column( name = "PK_ID_GRUPO_PERFIL" )
	private Long id;

	@Column( name = "NO_GRUPO_PERFIL" )
	private String noGrupoPerfil;

	@NotAudited
	@ManyToMany
	@JoinTable( name = "grupo_perfil_x_perfil", joinColumns = @JoinColumn( name = "pk_id_grupo_perfil" ), inverseJoinColumns = @JoinColumn( name = "pk_id_perfil" ) )
	private List<Perfil> perfis = new ArrayList<Perfil>();

	@NotAudited
	@ManyToMany
	@JoinTable( name = "usuario_x_grupo_perfil", joinColumns = @JoinColumn( name = "pk_id_grupo_perfil" ), inverseJoinColumns = @JoinColumn( name = "pk_id_usuario" ) )
	private List<Usuario> usuarios;

	@NotAudited
	@ManyToOne
	@JoinColumn( name = "ID_PRESTADORA" )
	private Prestadora prestadora;

	public GrupoPerfil() {}

	public GrupoPerfil( Long id ) {
		this.id = id;
	}

	public GrupoPerfil( Long id, String noGrupoPerfil ) {
		this.id = id;
		this.noGrupoPerfil = noGrupoPerfil;
	}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getNoGrupoPerfil() {
		return this.noGrupoPerfil;
	}

	public void setNoGrupoPerfil( String noGrupoPerfil ) {
		this.noGrupoPerfil = noGrupoPerfil;
	}

	public Prestadora getPrestadora() {
		return prestadora;
	}

	public void setPrestadora( Prestadora prestadora ) {
		this.prestadora = prestadora;
	}

	public List<Perfil> getPerfis() {
		if ( perfis == null )
			perfis = new ArrayList<Perfil>();
		return perfis;
	}

	public void setPerfis( List<Perfil> perfis ) {
		this.perfis = perfis;
	}

	public List<Usuario> getUsuarios() {
		if ( usuarios == null )
			usuarios = new ArrayList<Usuario>();
		return usuarios;
	}

	public void setUsuarios( List<Usuario> usuarios ) {
		this.usuarios = usuarios;
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
		GrupoPerfil other = (GrupoPerfil) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

}