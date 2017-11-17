package br.com.cleartech.pgmc.mgu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * The persistent class for the PERFIL database table.
 * 
 */
@Audited
@Entity
@Table( name = "PERFIL" )
@SequenceGenerator( name = "SEQ_PERFIL", sequenceName = "SEQ_PERFIL", initialValue = 1, allocationSize = 1 )
public class Perfil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8303361761007768685L;

	@Id
	@GeneratedValue( generator = "SEQ_PERFIL" )
	@Column( name = "PK_ID_PERFIL" )
	private Long id;

	@Column( name = "DC_PERFIL" )
	private String dcPerfil;

	@Column( name = "DC_DESCRICAO" )
	private String dcDescricao;

	@NotAudited
	@ManyToMany
	@JoinTable( name = "grupo_perfil_x_perfil", joinColumns = @JoinColumn( name = "pk_id_perfil" ), inverseJoinColumns = @JoinColumn( name = "pk_id_grupo_perfil" ) )
	private List<GrupoPerfil> grupoPerfis;

	@NotAudited
	@ManyToMany
	@JoinTable( name = "PERFIL_ACESSO_OPER", joinColumns = @JoinColumn( name = "PK_ID_PERFIL" ), inverseJoinColumns = @JoinColumn( name = "PK_ACESSO_OPER" ) )
	private List<AcessoOperadora> acessoOperadoras;

	@OrderBy
	@ManyToOne
	@JoinColumn( name = "FK_ID_SISTEMA" )
	private Sistema sistema;

	@NotAudited
	@OneToMany( mappedBy = "perfil", fetch = FetchType.LAZY )
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	public Perfil() {}

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getDcDescricao() {
		return dcDescricao;
	}

	public void setDcDescricao( String dcDescricao ) {
		this.dcDescricao = dcDescricao;
	}

	public String getDcPerfil() {
		return this.dcPerfil;
	}

	public void setDcPerfil( String dcPerfil ) {
		this.dcPerfil = dcPerfil;
	}

	public Sistema getSistema() {
		return this.sistema;
	}

	public void setSistema( Sistema sistema ) {
		this.sistema = sistema;
	}

	public List<GrupoPerfil> getGrupoPerfis() {
		return grupoPerfis;
	}

	public void setGrupoPerfis( List<GrupoPerfil> grupoPerfis ) {
		this.grupoPerfis = grupoPerfis;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios( List<Usuario> usuarios ) {
		this.usuarios = usuarios;
	}

	public List<AcessoOperadora> getAcessoOperadoras() {
		if ( acessoOperadoras == null )
			acessoOperadoras = new ArrayList<AcessoOperadora>();
		return acessoOperadoras;
	}

	public void setAcessoOperadoras( List<AcessoOperadora> acessoOperadoras ) {
		this.acessoOperadoras = acessoOperadoras;
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
		Perfil other = (Perfil) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		return true;
	}

}